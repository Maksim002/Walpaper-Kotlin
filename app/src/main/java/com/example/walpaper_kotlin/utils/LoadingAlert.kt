package com.example.walpaper_kotlin.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.walpaper_kotlin.R

class LoadingAlert(private val activity: Activity) {
    private lateinit var dialog: AlertDialog

    init {
        try {
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val view = inflater.inflate(R.layout.load_alert, null)
            builder.setView(view)
            builder.setCancelable(false)
            dialog = builder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        } catch (e: Exception) {
        }

    }

    fun show() {
        try {
            dialog.show()
        } catch (e: Exception) {

        }
    }

    fun hide() {
        try {
            dialog.dismiss()
        } catch (e: Exception) {

        }
    }
}