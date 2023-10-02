package com.davidlopez.stores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidlopez.stores.databinding.ItemStoreBinding

class NotasAdapter(private var notas:MutableList<NotasEntity>, private var listener: OnClickListener):
    RecyclerView.Adapter<NotasAdapter.ViewHolder>() {

    private lateinit var mContex:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       mContex=parent.context

        val view =LayoutInflater.from(mContex).inflate(R.layout.item_store,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = notas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notas=notas.get(position)

        with(holder){

            setListener(notas)
            binding.tvName.text=notas.name
        }

    }

    fun add(nota: NotasEntity) {
        notas.add(nota)
        notifyDataSetChanged()//refrecara la vista del adaptador
    }

    fun setNotas(notas: MutableList<NotasEntity>) {
        this.notas=notas
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding=ItemStoreBinding.bind(view)

        fun setListener(notasEntity: NotasEntity){
            binding.root.setOnClickListener { listener.onClick(notasEntity) }
        }

    }
}