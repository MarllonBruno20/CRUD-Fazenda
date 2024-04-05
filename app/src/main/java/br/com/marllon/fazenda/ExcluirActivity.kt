package br.com.marllon.fazenda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ExcluirActivity : AppCompatActivity() {

    private lateinit var et_registro : EditText
    private lateinit var btn_excluir : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excluir)

        et_registro = findViewById(R.id.editText_RegistroExcluir)
        btn_excluir = findViewById(R.id.btn_excluirRegistro)

        btn_excluir.setOnClickListener {
            val registro = et_registro.text.toString()
            val fazendaDAO = FazendaDAO(this)

            if (registro.isNotEmpty()) {
                val sucesso = fazendaDAO.deletar(registro)
                if (sucesso) {
                    Toast.makeText(this, "Fazenda deletada com sucesso!", Toast.LENGTH_LONG).show()
                    limparCampos()
                } else {
                    Toast.makeText(this, "Falha ao deletar a fazenda!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun limparCampos() {
        et_registro.text.clear()
    }

}