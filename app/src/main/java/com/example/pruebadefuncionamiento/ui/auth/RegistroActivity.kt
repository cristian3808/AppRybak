package com.example.pruebadefuncionamiento.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pruebadefuncionamiento.R
import com.example.pruebadefuncionamiento.SupaBaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class RegistroActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var etReContrasena: EditText
    private lateinit var checkTerminos: CheckBox
    private lateinit var btnRegistro: Button
    private lateinit var tvCuenta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_registro)

        //Manejo del Scroll Adaptable por teclado
        val rootView = findViewById<ViewGroup>(R.id.main)

        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())

            val bottomPadding = maxOf(systemBars.bottom, imeInsets.bottom)

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                bottomPadding
            )

            insets
        }

        //Referenciar los elementos presentes en el XML
        etNombre = findViewById(R.id.re_nombres)
        etApellidos = findViewById(R.id.re_apellidos)
        etCorreo = findViewById(R.id.re_correo)
        etContrasena = findViewById(R.id.re_contrasena)
        etReContrasena = findViewById(R.id.re_recontrasena)
        checkTerminos = findViewById(R.id.check_terminos)
        btnRegistro = findViewById(R.id.btn_registro)
        tvCuenta = findViewById(R.id.re_cuenta)

        //Listener del botón de registro
        btnRegistro.setOnClickListener {

            val nombres = etNombre.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()
            val reContrasena = etReContrasena.text.toString().trim()

            //Validaciones locales de los campos
            if (
                nombres.isEmpty() ||
                apellidos.isEmpty() ||
                correo.isEmpty() ||
                contrasena.isEmpty() ||
                reContrasena.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Por favor, complete todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (contrasena != reContrasena) {

                Toast.makeText(
                    this,
                    "Las contraseñas no coinciden",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (contrasena.length < 8) {

                Toast.makeText(
                    this,
                    "La contraseña debe tener al menos 8 caracteres",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (!checkTerminos.isChecked) {

                Toast.makeText(
                    this,
                    "Debe aceptar los términos y condiciones",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            //Registrar el usuario en Supabase
            lifecycleScope.launch {

                try {

                    SupaBaseClient.client.postgrest["usuarios"].insert(

                        buildJsonObject {

                            put("nombres", nombres)
                            put("apellidos", apellidos)

                        }
                    )

                    //Paso 3: Redirigir al usuario a login
                    Toast.makeText(
                        this@RegistroActivity,
                        "Registro exitoso",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this@RegistroActivity,
                            LoginActivity::class.java
                        )
                    )

                    finish()

                } catch (e: Exception) {

                    e.printStackTrace()

                    android.util.Log.e(
                        "SUPABASE_ERROR",
                        e.message.toString()
                    )

                    Toast.makeText(
                        this@RegistroActivity,
                        e.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        //Listener del texto de cuenta -> login
        tvCuenta.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

