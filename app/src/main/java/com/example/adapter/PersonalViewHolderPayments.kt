package com.example.tsj.adapters.pesonal

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.adapter.InvoiceListener
import com.example.service.model.Urls
import com.example.service.model.WalModel
import com.example.walpaper_kotlin.R
import kotlinx.android.synthetic.main.item_personal.view.*

class PersonalViewHolderPayments(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageIcon = itemView.findViewById<ImageView>(R.id.iconImage)
    var image = itemView.findViewById<ImageView>(R.id.image_installation)

    fun bind(item: Urls, listener: InvoiceListener){
            Glide.with(imageIcon)
                .load(item.small)
                .apply(RequestOptions())
                .transform(
                    CenterCrop(),
                    RoundedCorners(10))
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progress_personal.visibility = View.VISIBLE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progress_personal.visibility = View.GONE
                        return false
                    }
                })
                .into(imageIcon)




        image.setOnClickListener {
            listener.onClickDelete(item)
        }
    }

}