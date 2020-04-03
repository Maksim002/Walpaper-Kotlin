package com.example.tsj.adapters.pesonal

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.walpaper_kotlin.R
import com.example.walpaper_kotlin.service.model.Urls
import com.example.walpaper_kotlin.service.model.WalModel

class PersonalViewHolderPayments(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    var imageIcon = itemView.findViewById<ImageView>(R.id.iconImage)


    fun bind(item: WalModel) {
        Glide.with(imageIcon)
            .load(item.urls?.thumb)
            .apply(RequestOptions())
            .transform(
                CenterCrop(),
                RoundedCorners(10)
            )
            .into(imageIcon)
    }
}