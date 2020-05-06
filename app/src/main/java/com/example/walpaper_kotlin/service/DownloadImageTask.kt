package com.example.walpaper_kotlin.service

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import com.example.walpaper_kotlin.ui.main.MainActivity
import java.io.IOException
import java.net.URL

internal class DownloadImageTask(var context: Context) : AsyncTask<String?, Void?, Bitmap?>() {

    override fun doInBackground(vararg params: String?): Bitmap? {
        val urldisplay = params[0]
        var mIcon11: Bitmap? = null
        try {
            val `in` = URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }
        return mIcon11
    }

    override fun onPostExecute(result: Bitmap?) {
        val wpManager = WallpaperManager.getInstance(context)
        // Set the wallpaper
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Create the pitch black bitmap
            // On Android N and above use the new API to set both the general system wallpaper and
            // the lock-screen-specific wallpaper
            try {
                wpManager.setBitmap(
                    result,
                    null,
                    true,
                    WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            try {
                wpManager.setBitmap(result)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        MainActivity.alert.hide()
    }
}