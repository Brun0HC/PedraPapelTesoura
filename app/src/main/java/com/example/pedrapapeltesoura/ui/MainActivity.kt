package com.example.pedrapapeltesoura.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pedrapapeltesoura.databinding.ActivityMainBinding
import com.example.pedrapapeltesoura.model.Constant.NUMERO_JOGADORES

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        amb.toolbarIn.toolbar.apply {
            subtitle = this@MainActivity.javaClass.simpleName
            setSupportActionBar(this)
        }
        //onOptionSelected()
        amb.umVumBt.setOnClickListener {
            iniciarJogo(2)
        }

        amb.umVdoisBt.setOnClickListener {
            iniciarJogo(3)
        }

    }
//    private fun onOptionSelected(){
//        amb.umVumBt.setOnClickListener {
//            iniciarJogo(2)
//        }
//
//        amb.umVdoisBt.setOnClickListener {
//            iniciarJogo(3)
//        }
//    }
    private fun iniciarJogo(numeroJogadores: Int) {
        val intent = Intent(this@MainActivity, GameActivity::class.java)
        intent.putExtra(NUMERO_JOGADORES, numeroJogadores)
        startActivity(intent)
    }
}