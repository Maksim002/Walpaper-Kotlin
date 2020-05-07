package com.example.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import com.example.walpaper_kotlin.R


class InstallationAlert (private val activity: Activity) {
    private lateinit var dialog: AlertDialog
    private lateinit var context: Context

    init {
        try {
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val view = inflater.inflate(R.layout.installation_alert, null)
            builder.setView(view)
            builder.setCancelable(false)
            dialog = builder.create()
            dialog.dismiss()
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

