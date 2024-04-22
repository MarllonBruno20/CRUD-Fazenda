package br.com.marllon.fazenda

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val caminhoDoArquivo = "MeuArquivo"
    private var arquivoExterno: File?=null

    lateinit var bt_adicionar : Button
    lateinit var bt_listar : Button
    lateinit var bt_excluir : Button
    lateinit var bt_alterar : Button
    lateinit var bt_backup : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_adicionar = findViewById(R.id.bt_adicionarMain)
        bt_listar = findViewById(R.id.bt_listarMain)
        bt_excluir = findViewById(R.id.bt_excluirMain)
        bt_alterar = findViewById(R.id.bt_alterarAlterar)
        bt_backup = findViewById(R.id.btn_backup)

        bt_adicionar.setOnClickListener{
            val intent = android.content.Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        bt_listar.setOnClickListener{
            val intent = android.content.Intent(this, ListarActivity::class.java)
            startActivity(intent)
        }

        bt_alterar.setOnClickListener{
            val intent = android.content.Intent(this, AlterarActivity::class.java)
            startActivity(intent)
        }

        bt_excluir.setOnClickListener{
            val intent = android.content.Intent(this, ExcluirActivity::class.java)
            startActivity(intent)
        }

        bt_backup.setOnClickListener{
            val fazendaDAO = FazendaDAO(this)
            val dados = fazendaDAO.fazerBackup()

            val nomeDoArquivo = "meuArquivoDeTexto.txt"
            arquivoExterno = File(getExternalFilesDir(caminhoDoArquivo), nomeDoArquivo)

            try {
                val fileOutputStream = FileOutputStream(arquivoExterno)
                fileOutputStream.write(dados.toByteArray())
                fileOutputStream.close()

                val intent = Intent(this, BackupActivity::class.java)
                intent.putExtra("caminhoArquivo", arquivoExterno!!.absolutePath)
                startActivity(intent)

                Toast.makeText(applicationContext, "Texto salvo com sucesso!", Toast.LENGTH_LONG)
                    .show()
            } catch (e: IOException) {
                Log.i("Erro", "Erro ao salvar o arquivo: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
