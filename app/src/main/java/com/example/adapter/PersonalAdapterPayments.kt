package com.example.tsj.adapters.pesonal


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.InvoiceListener
import com.example.service.model.Urls
import com.example.service.model.WalModel
import com.example.walpaper_kotlin.R

class PersonalAdapterPayments(private val listener: InvoiceListener) : RecyclerView.Adapter<PersonalViewHolderPayments>() {

    private var model: ArrayList<Urls> = ArrayList()

    fun listUpdate(list: ArrayList<Urls>) {
        model.clear()
        notifyDataSetChanged()
        model = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalViewHolderPayments {
        return PersonalViewHolderPayments(
            LayoutInflater.from(parent.context).inflate(R.layout.item_personal, parent, false))
    }

    override fun getItemCount(): Int {
        return model.size
    }

    fun addItem(list: List<Urls>) {
            model.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holderPlateiPersonal: PersonalViewHolderPayments, position: Int) {
        //todo
        holderPlateiPersonal.bind(model.get(position),listener)
    }
}