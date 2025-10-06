package com.example.posyanduhb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReservasiBerhasilActivity : AppCompatActivity() {
    
    private lateinit var userPreferences: UserPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservasi_berhasil)

        // Initialize UserPreferences
        userPreferences = UserPreferences.getInstance(this)
        
        // Update greeting dengan username
        updateUserGreeting()
        
        // Update data reservasi dari intent
        updateReservasiData()

        val btnKembali = findViewById<Button>(R.id.btnKembali)
        val btnBeranda = findViewById<Button>(R.id.btnBeranda)

        btnKembali.setOnClickListener {
            finish() // balik ke halaman sebelumnya
        }

        btnBeranda.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Update greeting dengan username dari SharedPreferences
     */
    private fun updateUserGreeting() {
        val username = userPreferences.getUsername()
        if (username.isNotEmpty()) {
            val tvUsername = findViewById<TextView>(R.id.tv_username_berhasil)
            tvUsername?.text = "Hallo,\n$username"
        }
    }
    
    /**
     * Update data reservasi dari intent
     */
    private fun updateReservasiData() {
        val namaBalita = intent.getStringExtra("namaBalita") ?: "Data tidak tersedia"
        val layanan = intent.getStringExtra("layanan") ?: "Data tidak tersedia"
        val tanggal = intent.getStringExtra("tanggal") ?: "Data tidak tersedia"
        val jam = intent.getStringExtra("jam") ?: "Data tidak tersedia"
        val nomorAntrian = intent.getStringExtra("nomorAntrian") ?: "A12"
        
        // Update data di layout
        findViewById<TextView>(R.id.tv_nomor_antrian_detail)?.text = nomorAntrian
        findViewById<TextView>(R.id.tv_nama_balita_detail)?.text = namaBalita
        findViewById<TextView>(R.id.tv_layanan_detail)?.text = layanan
        findViewById<TextView>(R.id.tv_tanggal_jam_detail)?.text = "$tanggal\n$jam"
    }
}
