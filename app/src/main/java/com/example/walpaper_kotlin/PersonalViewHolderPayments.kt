package com.example.tsj.adapters.pesonal

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.walpaper_kotlin.R

class PersonalViewHolderPayments(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageIcon = itemView.findViewById<ImageView>(R.id.iconImage)
    var image = itemView.findViewById<ImageView>(R.id.image_installation)

    fun bind(
        item: String,
        listener: InvoiceListener
    ) {
        Glide.with(imageIcon)
            .load(item)
            .apply(RequestOptions())
            .transform(
                CenterCrop(),
                RoundedCorners(10)
            )
            .into(imageIcon)


        image.setOnClickListener {
            listener.onClickDelete(item)
        }
    }
}