package com.example.simnumber.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simnumber.R
import com.example.simnumber.model.Contato
import kotlinx.android.synthetic.main.item_list_contato.view.*

class ContatoListAdapter(contatos: List<Contato>, internal var context: Context)
    : RecyclerView.Adapter<ContatoListAdapter.ViewHolder>(){

    fun inserirdados(contatos:ArrayList<Contato>){
        contatoList=contatos
        this.notifyDataSetChanged()
    }

    internal var contatoList: List<Contato> = ArrayList<Contato>()
    init {
        this.contatoList = contatos
    }

    // Aqui é onde o viewholder é criado a partir do layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_contato, parent, false)
        return ViewHolder(view)
    }

    // Nessa parte é onde se modifica o item do viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contato = contatoList[position]
        holder.apply {
            nome.text = contato.nome
            numero.text = contato.numero
        }
    }

    // Devolve quantidade de itens do nameList
    override fun getItemCount(): Int {
        return contatoList.size
    }

    // Aqui é a criação dos itens do viewholder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nome = view.nome_contato
        var numero = view.numero_contato
    }
}