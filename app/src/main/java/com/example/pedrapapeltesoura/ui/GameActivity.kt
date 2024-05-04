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
    private var mao: JogadasMao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(agb.root)
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
            mao?.let { maoUsuario ->
                val maoAplicativo = jogadaAplicativo()
                exibirResultado(dueloJokenpo(maoUsuario, maoAplicativo), maoAplicativo)
            } ?: run {
                Toast.makeText(this, "Selecione uma jogada antes de jogar!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dueloJokenpo(maoUsuario: JogadasMao, maoAplicativo: JogadasMao): String {
        return when {
            maoUsuario == maoAplicativo -> "empate"
            maoUsuario == JogadasMao.PEDRA && maoAplicativo == JogadasMao.TESOURA ||
            maoUsuario == JogadasMao.PAPEL && maoAplicativo == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.TESOURA && maoAplicativo == JogadasMao.PAPEL -> "vitoria"
            else -> "derrota"
        }
    }

    private fun jogadaAplicativo(): JogadasMao {
        val maos = JogadasMao.values()
        return maos.random()
    }

    private fun exibirResultado(resultado: String, maoAplicativo: JogadasMao) {
        val mensagem = when (resultado) {
            "empate" -> "O jogo terminou empatado, o adversário também escolheu ${maoAplicativo.name}"
            "vitoria" -> "Você venceu, o adversário escolheu ${maoAplicativo.name}"
            else -> "Você perdeu, o adversário escolheu ${maoAplicativo.name}"
        }
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}
