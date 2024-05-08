package com.example.pedrapapeltesoura.ui
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.pedrapapeltesoura.R
import com.example.pedrapapeltesoura.databinding.ActivityGameBinding
import com.example.pedrapapeltesoura.jogadasMao.JogadasMao
import com.example.pedrapapeltesoura.model.Constant.NUMERO_JOGADORES

class GameActivity : AppCompatActivity() {
    private val agb: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }
    private var mao: JogadasMao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(agb.root)

        val numeroJogadores = intent.getIntExtra(NUMERO_JOGADORES, 0)

        if (numeroJogadores == 2){
            iniciarJogo1v1()}
        else if(numeroJogadores == 3){
            iniciarJogo1v2()}
        else{
            Toast.makeText(this, "ERRO DA INTENT", Toast.LENGTH_LONG).show()
        }
    }
    private fun iniciarJogo1v1() {
        setupUI()
        agb.jogarBt.setOnClickListener {
            val bot = jogadaBot()
            dueloUmVsUm(mao, bot)
            agb.imgBot.setImageResource(getEscolhaBot(bot))
            agb.botTv.visibility= View.VISIBLE
            agb.imgBot.visibility = View.VISIBLE
        }

    }
    private fun iniciarJogo1v2() {
        setupUI()
        agb.jogarBt.setOnClickListener {
            val bot1 = jogadaBot()
            val bot2 = jogadaBot()
            dueloUmVsDois(mao, bot1, bot2)

            agb.imgBot.setImageResource(getEscolhaBot(bot1))
            agb.botTv.visibility= View.VISIBLE
            agb.imgBot.visibility = View.VISIBLE
            agb.imgBot2.setImageResource(getEscolhaBot(bot2))
            agb.botTv2.visibility= View.VISIBLE
            agb.imgBot2.visibility = View.VISIBLE
        }

    }

    private fun setupUI() {
        agb.toolbarIn.toolbar.apply {
            title = getString(R.string.app_name)
            subtitle = this@GameActivity.javaClass.simpleName
            setSupportActionBar(this)
        }

        agb.imgPedra.setOnClickListener {
            mao = JogadasMao.PEDRA
        }

        agb.imgPapel.setOnClickListener {
            mao = JogadasMao.PAPEL
        }

        agb.imgTesoura.setOnClickListener {
            mao = JogadasMao.TESOURA
        }

        agb.imgLagarto.setOnClickListener {
            mao = JogadasMao.LAGARTO
        }

        agb.imgSpock.setOnClickListener {
            mao = JogadasMao.SPOCK
        }
    }

    private fun jogadaBot(): JogadasMao {
        val mao = JogadasMao.entries
        return mao.random()
    }
    private fun dueloUmVsUm(maoUsuario: JogadasMao?, maoBot: JogadasMao): Any {
        return when {
            maoUsuario == maoBot -> exibirResultado("empate", maoBot)
            maoUsuario == JogadasMao.PEDRA && maoBot == JogadasMao.TESOURA || maoUsuario == JogadasMao.PEDRA && maoBot == JogadasMao.LAGARTO ||
            maoUsuario == JogadasMao.PAPEL && maoBot == JogadasMao.PEDRA || maoUsuario == JogadasMao.PAPEL && maoBot == JogadasMao.SPOCK ||
            maoUsuario == JogadasMao.TESOURA && maoBot == JogadasMao.PAPEL || maoUsuario == JogadasMao.TESOURA && maoBot == JogadasMao.LAGARTO ||
            maoUsuario == JogadasMao.LAGARTO && maoBot == JogadasMao.PAPEL || maoUsuario == JogadasMao.LAGARTO && maoBot == JogadasMao.SPOCK ||
            maoUsuario == JogadasMao.SPOCK && maoBot == JogadasMao.PEDRA || maoUsuario == JogadasMao.SPOCK && maoBot == JogadasMao.TESOURA
            -> exibirResultado("vitória", maoBot)
            else -> exibirResultado("derrota", maoBot)
        }
    }
    private fun dueloUmVsDois(maoUsuario: JogadasMao?, bot1: JogadasMao, bot2: JogadasMao):Any {
        return when {
            // SÓ EMPATES
//            maoUsuario == bot1 && maoUsuario == bot2 -> exibirResultado("empate",null)
//            maoUsuario == JogadasMao.PEDRA && bot1 == JogadasMao.PEDRA && bot2 == JogadasMao.TESOURA ||
//            maoUsuario == JogadasMao.PEDRA && bot1 == JogadasMao.TESOURA && bot2 == JogadasMao.PEDRA ||
//            maoUsuario == JogadasMao.TESOURA && bot1 == JogadasMao.TESOURA && bot2 == JogadasMao.PAPEL ||
//            maoUsuario == JogadasMao.TESOURA && bot1 == JogadasMao.PAPEL && bot2 == JogadasMao.TESOURA ||
//            maoUsuario == JogadasMao.PAPEL && bot1 == JogadasMao.PAPEL && bot2 == JogadasMao.PEDRA ||
//            maoUsuario == JogadasMao.PAPEL && bot1 == JogadasMao.PEDRA && bot2 == JogadasMao.PAPEL
//            -> exibirResultado("empate",null)
            //maoUsuario != bot1 && maoUsuario != bot2 && bot1 != bot2 -> exibirResultado("empate",null)

            // SÓ VITÓRIAS
            maoUsuario == JogadasMao.PAPEL && bot1 == JogadasMao.PEDRA && bot2 == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.PAPEL && bot1 == JogadasMao.PEDRA && bot2 == JogadasMao.SPOCK ||
            maoUsuario == JogadasMao.PAPEL && bot1 == JogadasMao.SPOCK && bot2 == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.PAPEL && bot1 == JogadasMao.SPOCK && bot2 == JogadasMao.SPOCK ||

            maoUsuario == JogadasMao.PEDRA && bot1 == JogadasMao.LAGARTO && bot2 == JogadasMao.LAGARTO ||
            maoUsuario == JogadasMao.PEDRA && bot1 == JogadasMao.TESOURA && bot2 == JogadasMao.TESOURA ||
            maoUsuario == JogadasMao.PEDRA && bot1 == JogadasMao.TESOURA && bot2 == JogadasMao.LAGARTO ||
            maoUsuario == JogadasMao.PEDRA && bot1 == JogadasMao.LAGARTO && bot2 == JogadasMao.TESOURA ||

            maoUsuario == JogadasMao.TESOURA && bot1 == JogadasMao.PAPEL && bot2 == JogadasMao.PAPEL ||
            maoUsuario == JogadasMao.TESOURA && bot1 == JogadasMao.PAPEL && bot2 == JogadasMao.LAGARTO ||
            maoUsuario == JogadasMao.TESOURA && bot1 == JogadasMao.LAGARTO && bot2 == JogadasMao.PAPEL ||
            maoUsuario == JogadasMao.TESOURA && bot1 == JogadasMao.LAGARTO && bot2 == JogadasMao.LAGARTO ||

            maoUsuario == JogadasMao.LAGARTO && bot1 == JogadasMao.TESOURA && bot2 == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.LAGARTO && bot1 == JogadasMao.PEDRA && bot2 == JogadasMao.TESOURA ||
            maoUsuario == JogadasMao.LAGARTO && bot1 == JogadasMao.PEDRA && bot2 == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.LAGARTO && bot1 == JogadasMao.TESOURA && bot2 == JogadasMao.TESOURA ||

            maoUsuario == JogadasMao.SPOCK && bot1 == JogadasMao.PAPEL && bot2 == JogadasMao.LAGARTO ||
            maoUsuario == JogadasMao.SPOCK && bot1 == JogadasMao.LAGARTO && bot2 == JogadasMao.PAPEL ||
            maoUsuario == JogadasMao.SPOCK && bot1 == JogadasMao.PAPEL && bot2 == JogadasMao.PAPEL ||
            maoUsuario == JogadasMao.SPOCK && bot1 == JogadasMao.LAGARTO && bot2 == JogadasMao.LAGARTO

            -> exibirResultado("vitória", null)

            // SÓ DERROTAS
            maoUsuario == JogadasMao.TESOURA && bot2 == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.TESOURA && bot1 == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.TESOURA && bot1 == JogadasMao.SPOCK ||
            maoUsuario == JogadasMao.TESOURA && bot2 == JogadasMao.SPOCK ||


            maoUsuario == JogadasMao.PAPEL && bot1 == JogadasMao.TESOURA ||
            maoUsuario == JogadasMao.PAPEL && bot2 == JogadasMao.TESOURA ||
            maoUsuario == JogadasMao.PAPEL && bot1 == JogadasMao.LAGARTO ||
            maoUsuario == JogadasMao.PAPEL && bot2 == JogadasMao.LAGARTO ||

            maoUsuario == JogadasMao.PEDRA && bot2 == JogadasMao.PAPEL ||
            maoUsuario == JogadasMao.PEDRA && bot1 == JogadasMao.PAPEL ||
            maoUsuario == JogadasMao.PEDRA && bot1 == JogadasMao.SPOCK ||
            maoUsuario == JogadasMao.PEDRA && bot2 == JogadasMao.SPOCK ||

            maoUsuario == JogadasMao.SPOCK && bot1 == JogadasMao.PAPEL ||
            maoUsuario == JogadasMao.SPOCK && bot1 == JogadasMao.LAGARTO ||
            maoUsuario == JogadasMao.SPOCK && bot2 == JogadasMao.PAPEL ||
            maoUsuario == JogadasMao.SPOCK && bot2 == JogadasMao.LAGARTO ||

            maoUsuario == JogadasMao.LAGARTO && bot1 == JogadasMao.TESOURA ||
            maoUsuario == JogadasMao.LAGARTO && bot1 == JogadasMao.PEDRA ||
            maoUsuario == JogadasMao.LAGARTO && bot2 == JogadasMao.TESOURA ||
            maoUsuario == JogadasMao.LAGARTO && bot2 == JogadasMao.PEDRA

            -> exibirResultado("derrota", null)
            else -> exibirResultado("empate", null)
        }

    }


    private fun exibirResultado(resultado: String, maoBot: JogadasMao?) {
        if(maoBot!=null){
            val mensagem = when (resultado) {
                "empate" -> "EMPATE, o adversário também escolheu ${maoBot.name}"
                "vitória" -> "ViTÓRIA, o adversário escolheu ${maoBot.name}"
                "derrota" -> "DERROTA, o adversário escolheu ${maoBot.name}"
                else -> "Resultado indeterminado"
            }
            Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()}
        else{
            val mensagem = when (resultado) {
                "empate" -> "EMPATE"
                "vitória" -> "ViTÓRIA"
                "derrota" -> "DERROTA"
                else -> "Resultado indeterminado"
            }
            Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()}
    }
    private fun getEscolhaBot(choice: JogadasMao): Int {
        return when (choice) {
            JogadasMao.PEDRA -> R.drawable.pedra
            JogadasMao.PAPEL-> R.drawable.papel
            JogadasMao.TESOURA -> R.drawable.tesoura
            JogadasMao.LAGARTO -> R.drawable.lizard
            JogadasMao.SPOCK -> R.drawable.spock
            else -> {
                Toast.makeText(this@GameActivity, "", Toast.LENGTH_LONG).show()
                R.drawable.tesoura
            }
        }
    }
}
