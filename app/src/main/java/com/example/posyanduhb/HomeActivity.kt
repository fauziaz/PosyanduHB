package com.example.posyanduhb

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

        // Panggil fungsi untuk menyiapkan menu saat activity dibuat
        setupOverflowMenu()

        // buka JadwalActivity ketika kartu/jadwal diketuk
        binding.imgJadwalPelaksanaan.setOnClickListener {
            startActivity(android.content.Intent(this, JadwalActivity::class.java))
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
                        // Aksi saat item "Bantuan" diklik
                        Toast.makeText(this, "Bantuan diklik", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.menu_hubungi_kami -> {
                        // Aksi saat item "Hubungi Kami" diklik
                        Toast.makeText(this, "Hubungi Kami diklik", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.menu_logout -> {
                        // Aksi saat item "Logout" diklik
                        Toast.makeText(this, "Logout diklik", Toast.LENGTH_SHORT).show()
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

