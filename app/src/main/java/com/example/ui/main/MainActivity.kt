package com.example.ui.main
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.utils.InstallationAlert
import com.example.walpaper_kotlin.R
import com.example.utils.LoadingAlert

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var alert: LoadingAlert
        lateinit var alert_ins: InstallationAlert
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alert = LoadingAlert(this)
        alert_ins = InstallationAlert(this)

            val navController = findNavController(R.id.nav_host_fragment)
            val appBarConfiguration = AppBarConfiguration(setOf(
                    R.id.blanc
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
