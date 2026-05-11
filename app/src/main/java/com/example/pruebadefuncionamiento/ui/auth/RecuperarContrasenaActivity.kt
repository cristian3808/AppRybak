package com.example.pruebadefuncionamiento.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebadefuncionamiento.R

class RecuperarContrasenaActivity : AppCompatActivity() {

    private lateinit var tvVolverLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperar_contrasena)

        tvVolverLogin = findViewById(R.id.tv_volver_login)

        tvVolverLogin.setOnClickListener {
            finish()
        }
    }
}