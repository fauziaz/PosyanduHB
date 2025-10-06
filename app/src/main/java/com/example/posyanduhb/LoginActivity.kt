package com.example.posyanduhb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    
    private lateinit var userPreferences: UserPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UserPreferences
        userPreferences = UserPreferences.getInstance(this)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegisterLink)

        // underline the register link so it looks like a clickable link
        tvRegister.paint.isUnderlineText = true

        btnLogin.setOnClickListener {
            val u = etUsername.text.toString().trim()
            val p = etPassword.text.toString().trim()
            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Masukkan username dan password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // Update username dari login (jika berbeda dengan yang tersimpan)
            val currentUsername = userPreferences.getUsername()
            if (currentUsername.isEmpty() || currentUsername != u) {
                // Jika belum ada data atau username berbeda, simpan username baru
                userPreferences.saveUserProfile(
                    username = u,
                    email = userPreferences.getEmail(),
                    dob = userPreferences.getDob(),
                    phone = userPreferences.getPhone()
                )
            }
            
            // For now, accept any non-empty credentials and go to HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
