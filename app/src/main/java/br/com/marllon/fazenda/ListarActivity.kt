package br.com.marllon.fazenda

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.marllon.fazenda.adapter.AdapterFazenda

class ListarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        val recyclerView_fazendas = findViewById<RecyclerView>(R.id.recyclerView_fazendas)
        recyclerView_fazendas.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView_fazendas.setHasFixedSize(true) //mais desempenho na lista

        //configurar o Adapter
        val fazendaDAO = FazendaDAO(this)
        val adapterFazenda = AdapterFazenda(this, fazendaDAO.listar())
        recyclerView_fazendas.adapter = adapterFazenda

    }
}