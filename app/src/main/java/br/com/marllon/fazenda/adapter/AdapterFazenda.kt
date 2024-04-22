package br.com.marllon.fazenda.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.marllon.fazenda.model.Fazenda
import br.com.marllon.fazenda.R

class AdapterFazenda(private val context : Context, private val fazenda : MutableList<Fazenda>) : RecyclerView.Adapter<AdapterFazenda.FazendaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FazendaViewHolder {

        val itemLista = LayoutInflater.from(context).inflate(R.layout.fazendas_item, parent, false)
        val holder = FazendaViewHolder(itemLista)
        return holder

    }

    override fun getItemCount(): Int = fazenda.size

    override fun onBindViewHolder(holder: FazendaViewHolder, position: Int) {
        holder.registro.text = fazenda[position].registro
        holder.nome.text = fazenda[position].nome
        holder.preco.text = fazenda[position].valor.toString()
        holder.latitude.text = fazenda[position].latitude.toString()
        holder.longitude.text = fazenda[position].longitude.toString()

    }

    inner class FazendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val registro = itemView.findViewById<TextView>(R.id.textView_Registro)
        val nome = itemView.findViewById<TextView>(R.id.textView_nomeFazenda)
        val preco = itemView.findViewById<TextView>(R.id.textView_Valor)
        val latitude = itemView.findViewById<TextView>(R.id.textViewLatitude)
        val longitude = itemView.findViewById<TextView>(R.id.textViewLongitude)

    }

}