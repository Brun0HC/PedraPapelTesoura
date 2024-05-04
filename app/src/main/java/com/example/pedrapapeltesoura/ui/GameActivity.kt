package com.example.pedrapapeltesoura.ui
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.pedrapapeltesoura.R
import com.example.pedrapapeltesoura.databinding.ActivityGameBinding
import com.example.pedrapapeltesoura.jogadasMao.JogadasMao

class GameActivity : AppCompatActivity() {
    private val agb: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }
    private var numeroJogadores: Int = 0
    private var mao: JogadasMao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(agb.root)

        val numeroJogadores = intent.getIntExtra("numeroJogadores", 2)

        when (numeroJogadores) {
            2 -> iniciarJogo1v1()
            //3 -> iniciarJogo1v1v1()
        }

    }

    private fun iniciarJogo1v1() {
        setupUI()
    }

    private fun setupUI() {
        agb.toolbarIn.toolbar.apply {
             title = getString(R.string.app_name)
            subtitle = this@GameActivity.javaClass.simpleName
            setSupportActionBar(this)
        }

        agb.imgStone.setOnClickListener {
            mao = JogadasMao.PEDRA
        }

        agb.imgPaper.setOnClickListener {
            mao = JogadasMao.PAPEL
        }

        agb.imgScizor.setOnClickListener {
            mao = JogadasMao.TESOURA
        }

        agb.jogarBt.setOnClickListener {
            val aplicativo1 = jogadaBot()
            mao?.let { it1 -> dueloJokenpo(it1, aplicativo1) }
        }
    }

    private fun jogadaBot(): JogadasMao {
        val maos = JogadasMao.entries
        return maos.random()
    }
    private fun dueloJokenpo(maoUsuario: JogadasMao, maoBot: JogadasMao): Any {
        return when {
            maoUsuario == maoBot -> exibirResultado("empate", maoBot)
            maoUsuario == JogadasMao.PEDRA && maoBot == JogadasMao.TESOURA ||
            maoUsuario == JogadasMao.PAPEL && maoBot == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.TESOURA && maoBot == JogadasMao.PAPEL -> exibirResultado("vitória", maoBot)
            else -> exibirResultado("derrota", maoBot)
        }
    }


    private fun exibirResultado(resultado: String, maoBot: JogadasMao) {
        val mensagem = when (resultado) {
            "empate" -> "O jogo terminou empatado, o adversário também escolheu ${maoBot.name}"
            "vitoria" -> "Você venceu, o adversário escolheu ${maoBot.name}"
            "derrota" -> "Você perdeu, o adversário escolheu ${maoBot.name}"
            else -> "Resultado indeterminado"
        }
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
