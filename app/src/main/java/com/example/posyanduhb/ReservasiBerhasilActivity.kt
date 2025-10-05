package com.example.posyanduhb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ReservasiBerhasilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservasi_berhasil)

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        val btnBeranda = findViewById<Button>(R.id.btnBeranda)

        btnKembali.setOnClickListener {
            finish() // balik ke halaman sebelumnya
        }

        btnBeranda.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
