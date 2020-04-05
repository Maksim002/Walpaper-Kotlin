package com.example.tsj.adapters.pesonal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walpaper_kotlin.R
import com.example.walpaper_kotlin.service.model.WalModel
import com.example.walpaper_kotlin.service.models.Example

class PersonalAdapterPayments() :
    RecyclerView.Adapter<PersonalViewHolderPayments>() {

    private var model: ArrayList<String> = ArrayList()

    fun listUpdate(list: ArrayList<String>) {
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

    fun addItem(list: List<String>) {
        for (im in list) {
            model.add(im)
        }
        notifyDataSetChanged()
    }

//    fun remuwList() {
//        val position = model.size -1
//        model.get(position)
//
//    }

    override fun onBindViewHolder(holderPlateiPersonal: PersonalViewHolderPayments, position: Int) {
        holderPlateiPersonal.bind(model.get(position));
    }
}