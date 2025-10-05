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
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // close this activity to prevent going back here with back button
        }
        // wire bottom nav/fab (if bottom navigation exists in this layout)
        setupBottomNavigation(this)
    }
}
