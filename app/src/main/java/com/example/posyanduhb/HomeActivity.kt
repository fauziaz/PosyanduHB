package com.example.posyanduhb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.example.posyanduhb.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences.getInstance(this)
        
        // Set username from saved preferences
        updateUserGreeting()

        setupOverflowMenu()

        // buka JadwalActivity ketika kartu/jadwal diklik
        binding.imgJadwalPelaksanaan.setOnClickListener {
            safeStartActivity(android.content.Intent(this, JadwalActivity::class.java))
        }

        // make the whole CardView clickable (safer if user taps outside the icon)
        try {
            binding.cardJadwalpemeriksaan.setOnClickListener {
                safeStartActivity(android.content.Intent(this, JadwalActivity::class.java))
            }
        } catch (e: Exception) {
            // fallback: find by id if view binding doesn't expose the card (older generated name)
            val card = findViewById<android.view.View>(R.id.card_jadwalpemeriksaan)
            card?.setOnClickListener {
                safeStartActivity(android.content.Intent(this, JadwalActivity::class.java))
            }
        }
        binding.cardArtikelMpasi.setOnClickListener {
            val url = "https://www.rspondokindah.co.id/id/news/menyiapkan-asupan-pertama-untuk-si-kecil"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            safeStartActivity(intent)
        }
        // Card Reservasi Online -> RiwayatActivity (lihat riwayat reservasi)
        binding.cardReservasionline.setOnClickListener {
            safeStartActivity(Intent(this, RiwayatActivity::class.java))
        }

        // Card Profil -> ProfileActivity
        binding.cardProfilakun.setOnClickListener {
            safeStartActivity(Intent(this, ProfileActivity::class.java))
        }

        // Set up bottom navigation and FAB if present
        setupBottomNavigation(this)
    }

    // Fungsi baru untuk menangani menu titik tiga (menu more)
    override fun onResume() {
        super.onResume()
        updateUserGreeting()
    }

    private fun updateUserGreeting() {
        val username = userPreferences.getUsername()
        binding.tvGreeting.text = "Hallo,\n$username"
    }

    private fun setupOverflowMenu() {

        binding.ivMoreOptions.setOnClickListener { view ->
            // 1. Buat instance PopupMenu
            val popupMenu = PopupMenu(this, view)

            // 2. Inflate (tampilkan) menu dari file XML
            popupMenu.menuInflater.inflate(R.menu.overflow_menu, popupMenu.menu)

            // 3. Tambahkan listener untuk menangani klik pada setiap item menu
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                // DEBUG: log clicked menu item id and title
                android.util.Log.d("HomeActivity", "Overflow clicked: id=${menuItem.itemId}, title=${menuItem.title}")

                // Primary: prefer ID-based handling. Secondary: fallback to title matching
                when (menuItem.itemId) {
                    R.id.menu_bantuan -> {
                        val intent = Intent(this, BantuanActivity::class.java)
                        safeStartActivity(intent)
                        true
                    }
                    R.id.menu_hubungi_kami -> {
                        val intent = Intent(this, HubungiKamiActivity::class.java)
                        safeStartActivity(intent)
                        Toast.makeText(this, "Membuka Hubungi Kami", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.menu_logout -> {
                        val intent = Intent(this, MainActivity::class.java)
                        safeStartActivity(intent)
                        true
                    }
                    else -> {
                        // Fallback: match by title text in case resource IDs got mixed up at build time
                        val title = menuItem.title?.toString()?.lowercase() ?: ""
                        when {
                            title.contains("hubungi") -> {
                                val intent = Intent(this, HubungiKamiActivity::class.java)
                                safeStartActivity(intent)
                                Toast.makeText(this, "Membuka Hubungi Kami", Toast.LENGTH_SHORT).show()
                                true
                            }
                            title.contains("bantuan") -> {
                                val intent = Intent(this, BantuanActivity::class.java)
                                safeStartActivity(intent)
                                true
                            }
                            title.contains("logout") -> {
                                val intent = Intent(this, MainActivity::class.java)
                                safeStartActivity(intent)
                                true
                            }
                            else -> false
                        }
                    }
                }
            }

            // 4. Tampilkan PopupMenu
            popupMenu.show()
        }
    }
}

// Helper: start activity safely from an Activity context; show toast/log on failure
private fun AppCompatActivity.safeStartActivity(intent: android.content.Intent) {
    try {
        this.startActivity(intent)
    } catch (e: Exception) {
        android.util.Log.e("HomeActivity", "Failed to start activity: ${e.message}", e)
        android.widget.Toast.makeText(this, "Tidak dapat membuka halaman (${e.message})", android.widget.Toast.LENGTH_SHORT).show()
    }
}

