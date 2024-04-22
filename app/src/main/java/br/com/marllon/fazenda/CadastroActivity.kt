package br.com.marllon.fazenda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.marllon.fazenda.model.Fazenda

class CadastroActivity : AppCompatActivity() {

    private lateinit var et_registro: EditText
    private lateinit var et_nome: EditText
    private lateinit var et_valor: EditText
    private lateinit var et_latitude: EditText
    private lateinit var et_longitude: EditText
    private lateinit var bt_cadastrar: Button
    private lateinit var bt_voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        et_registro = findViewById(R.id.et_registro)
        et_nome = findViewById(R.id.et_nome)
        et_valor = findViewById(R.id.et_valor)
        et_latitude = findViewById(R.id.et_latitude)
        et_longitude = findViewById(R.id.et_longitude)
        bt_cadastrar = findViewById(R.id.bt_cadastrar)
        bt_voltar = findViewById(R.id.bt_voltar)

        bt_cadastrar.setOnClickListener {
            cadastrarFazenda()
        }

        bt_voltar.setOnClickListener {
            finish()
        }
    }

    private fun cadastrarFazenda() {
        try {
            val registro = et_registro.text.toString()
            val nome = et_nome.text.toString()
            val valor = et_valor.text.toString().toFloatOrNull() ?: 0f
            val latitude = et_latitude.text.toString().toFloatOrNull() ?: 0f
            val longitude = et_longitude.text.toString().toFloatOrNull() ?: 0f

            val fazenda = Fazenda(registro, nome, valor, latitude, longitude)
            val fazendaDAO = FazendaDAO(this)

            val inseriu = fazendaDAO.insert(fazenda)
            if (inseriu) {
                limparCampos()
                Toast.makeText(this, "Fazenda cadastrada com sucesso!", Toast.LENGTH_LONG).show()
            }
        } catch (e: InsercaoBancoException) {
            Toast.makeText(this, e.message ?: "Erro ao cadastrar fazenda.", Toast.LENGTH_LONG).show()
        }
    }

    private fun limparCampos() {
        et_registro.text.clear()
        et_nome.text.clear()
        et_valor.text.clear()
        et_latitude.text.clear()
        et_longitude.text.clear()
    }


}
