package com.example.posyanduhb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posyanduhb.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    // Mendeklarasikan variabel binding untuk mengakses komponen layout
    // Konsep ini disebut "View Binding", yang lebih modern dan aman
    // dibandingkan findViewById().
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menghubungkan layout activity_home.xml dengan kelas Kotlin ini
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Di sinilah Anda akan menambahkan logika untuk tombol-tombol
        // dan interaksi lainnya.
        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Contoh: Menambahkan aksi klik untuk tombol (CardView)
        // Anda bisa membuat fungsi ini untuk mengelola semua Intent
        // atau navigasi ke halaman lain.

        // binding.cardJadwal.setOnClickListener {
        //     val intent = Intent(this, JadwalActivity::class.java)
        //     startActivity(intent)
        // }

        // binding.cardReservasi.setOnClickListener {
        //     // Pindah ke halaman Reservasi
        // }

        // dan seterusnya untuk komponen interaktif lainnya.
    }
}
