package br.com.marllon.fazenda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AlterarActivity : AppCompatActivity() {

    private lateinit var et_registro : EditText
    private lateinit var et_nome : EditText
    private lateinit var btn_alterar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alterar)

         et_registro = findViewById(R.id.editText_AlterarRegistro)
         et_nome = findViewById(R.id.editText_AlterarNome)
         btn_alterar = findViewById(R.id.btn_Alterar)

        btn_alterar.setOnClickListener {
            val novoNome = et_nome.text.toString()
            val registro = et_registro.text.toString()
            val fazendaDAO = FazendaDAO(this)

            if (novoNome.isNotEmpty() && registro.isNotEmpty()) {
                val sucesso = fazendaDAO.alterar(registro, novoNome)
                if (sucesso) {
                    Toast.makeText(this, "Fazenda atualizada com sucesso!", Toast.LENGTH_LONG).show()
                    limparCampos()
                } else {
                    Toast.makeText(this, "Falha ao atualizar a fazenda!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun limparCampos() {
        et_registro.text.clear()
        et_nome.text.clear()
    }
}