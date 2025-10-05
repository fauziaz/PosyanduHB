package com.example.posyanduhb

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.example.posyanduhb.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupOverflowMenu()

        // buka JadwalActivity ketika kartu/jadwal dikeme
        binding.imgJadwalPelaksanaan.setOnClickListener {
            startActivity(android.content.Intent(this, JadwalActivity::class.java))
        }

        // make the whole CardView clickable (safer if user taps outside the icon)
        try {
            binding.cardJadwalpelaksanaan.setOnClickListener {
                startActivity(android.content.Intent(this, JadwalActivity::class.java))
            }
        } catch (e: Exception) {
            // fallback: find by id if view binding doesn't expose the card (older generated name)
            val card = findViewById<android.view.View>(R.id.card_jadwalpelaksanaan)
            card?.setOnClickListener {
                startActivity(android.content.Intent(this, JadwalActivity::class.java))
            }
        }
        binding.cardArtikelMpasi.setOnClickListener {
            val url = "https://www.rspondokindah.co.id/id/news/menyiapkan-asupan-pertama-untuk-si-kecil"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    // Fungsi baru untuk menangani menu titik tiga (menu more)
    private fun setupOverflowMenu() {

        binding.ivMoreOptions.setOnClickListener { view ->
            // 1. Buat instance PopupMenu
            val popupMenu = PopupMenu(this, view)

            // 2. Inflate (tampilkan) menu dari file XML
            popupMenu.menuInflater.inflate(R.menu.overflow_menu, popupMenu.menu)

            // 3. Tambahkan listener untuk menangani klik pada setiap item menu
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                when (menuItem.itemId) {
                    R.id.menu_bantuan -> {
                        // Membuat Intent untuk membuka BantuanActivity
                        val intent = Intent(this, BantuanActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.menu_hubungi_kami -> {
                        // Aksi saat item "Hubungi Kami" diklik
                        val intent = Intent(this, JadwalActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.menu_logout -> {
                        // Aksi saat item "Logout" diklik
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }

            // 4. Tampilkan PopupMenu
            popupMenu.show()
        }
    }
}

