package com.example.pruebadefuncionamiento.ui.inicio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebadefuncionamiento.R
import com.example.pruebadefuncionamiento.ui.auth.LoginActivity
import com.example.pruebadefuncionamiento.ui.auth.RegistroActivity
import kotlin.jvm.java

class InicioActivity : AppCompatActivity() {

    private lateinit var btnIngresar: Button
    private lateinit var TvRegistrate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

        btnIngresar = findViewById(R.id.btn_ingresar)
        btnIngresar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        TvRegistrate = findViewById(R.id.tv_registrate)
        TvRegistrate.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
            finish()
        }
    }
}