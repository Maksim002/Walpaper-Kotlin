package com.example.tsj.adapters.pesonal

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.walpaper_kotlin.R
import com.example.walpaper_kotlin.service.WalpaperManager
import com.example.walpaper_kotlin.service.model.Urls
import com.example.walpaper_kotlin.service.model.WalModel
import kotlinx.android.synthetic.main.item_personal.*

class PersonalViewHolderPayments(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var context: Context

    var imageIcon = itemView.findViewById<ImageView>(R.id.iconImage)
    var image = itemView.findViewById<ImageView>(R.id.image_installation)

    fun bind(item: String) {
        Glide.with(imageIcon)
            .load(item)
            .apply(RequestOptions())
            .transform(
                CenterCrop(),
                RoundedCorners(10)
            )
            .into(imageIcon)

//        image.setOnClickListener {
//            when (it.id){
//                R.id.image -> setWallpaper()
//            }
//        }
//    }
//    private fun setWallpaper() {
//        image.isEnabled = false
//        val bitmap: Bitmap = imageIcon.drawable.toBitmap()
//        val task = SetWallpeperTask(context, bitmap)
//        task.execute(true)
//
//    }
//    companion object{
//        class SetWallpeperTask internal constructor(private val context: Context, private val bitmap: Bitmap): AsyncTask<Boolean, String, String>() {
//            override fun doInBackground(vararg params: Boolean?): String {
//                val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(context)
//                wallpaperManager.setBitmap(bitmap)
//                return "wallpaper set"
//            }
//        }
    }
}