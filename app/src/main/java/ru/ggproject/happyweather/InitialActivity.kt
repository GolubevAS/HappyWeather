package ru.ggproject.happyweather

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder


const val GEO_LOCATION_REQUEST_COD_SUCCESS = 1 //код успеха (пользователь согласился)

class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        checkPermission()
    }

    //-- initial activity code---
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //если пользователь дает разрешение на использование гео-данных то он переходит в MainActivity
        if(requestCode == GEO_LOCATION_REQUEST_COD_SUCCESS && permissions.isNotEmpty()) {
            val intent = Intent (this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    //запрос от пользователя разрешений
    private fun checkPermission() {
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ) {

            MaterialAlertDialogBuilder(this)
                .setTitle("Необходимы гео-данные")
                .setMessage("Пожалуйста, разрешите доступ к Вашим гео-данным для продолжения работы приложения")
                .setPositiveButton("OK") { _,_ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS
                    )
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS
                    )
                }
                .setNegativeButton("Cancel"){dialog,_ -> dialog.dismiss()}
                .create()
                .show()
        }
    }


}