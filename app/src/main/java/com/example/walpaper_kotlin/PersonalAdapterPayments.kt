package com.example.tsj.adapters.pesonal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walpaper_kotlin.R
import com.example.walpaper_kotlin.service.model.WalModel

class PersonalAdapterPayments() :
    RecyclerView.Adapter<PersonalViewHolderPayments>() {

    private var model: List<WalModel> = ArrayList()

    fun listUpdate(list: List<WalModel>) {
        model = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalViewHolderPayments {
        return PersonalViewHolderPayments(
            LayoutInflater.from(parent.context).inflate(R.layout.item_personal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return model.size
    }

    fun add(wo: WalModel){
        model = model + wo
        notifyDataSetChanged()
    }

    fun addItem(list: List<WalModel>) {
        for (im in list) {
            add(im)
        }
    }

    fun addBittom(){
        addItem(model)
    }


    override fun onBindViewHolder(holderPlateiPersonal: PersonalViewHolderPayments, position: Int) {
        holderPlateiPersonal.bind(model.get(position));
    }
}