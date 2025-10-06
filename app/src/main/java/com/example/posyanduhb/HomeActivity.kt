package com.example.posyanduhb

import android.content.ActivityNotFoundException
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

        // Initialize UserPreferences
        userPreferences = UserPreferences.getInstance(this)
        
        // Update greeting dengan username
        updateUserGreeting()

        // Memanggil satu fungsi utama untuk mengatur semua listener klik
        setupClickListeners()
    }

    /**
     * Update greeting dengan username dari SharedPreferences
     */
    private fun updateUserGreeting() {
        val username = userPreferences.getUsername()
        if (username.isNotEmpty()) {
            // Update greeting di home menggunakan binding
            binding.tvGreeting.text = "Hallo,\n$username"
        }
    }

    /**
     * Fungsi ini mengatur semua listener klik untuk view di halaman utama.
     */
    private fun setupClickListeners() {
        // Listener untuk menu titik tiga (overflow menu)
        binding.ivMoreOptions.setOnClickListener {
            showOverflowMenu(it)
        }

        // --- Listener untuk Card Menu Utama ---
        
        // Card Jadwal Pemeriksaan -> ke JadwalActivity
        binding.cardJadwalpemeriksaan.setOnClickListener {
            startActivity(Intent(this, JadwalActivity::class.java))
        }

        // Card Reservasi Online -> ke RiwayatActivity (history reservasi)
        binding.cardReservasionline.setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }

        // Card Profil Akun -> ke ProfileActivity
        binding.cardProfilakun.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // --- Listener untuk Artikel ---
        binding.cardArtikelMpasi.setOnClickListener {
            openUrl("https://www.rspondokindah.co.id/id/news/menyiapkan-asupan-pertama-untuk-si-kecil")
        }

        binding.cardArtikelImunisasi.setOnClickListener {
            openUrl("https://primayahospital.com/layanan/imunisasi-anak/")
        }

        binding.cardArtikelTumbuh.setOnClickListener {
            openUrl("https://rspcl.ihc.id/artikel-detail-602-Tumbuh-Kembang-Anak-dan-Tahapan-Dari-Bayi-Hingga-Balita-.html")
        }

        // --- Setup Bottom Navigation ---
        setupBottomNavigation(this)
    }

    /**
     * Fungsi pembantu untuk membuka URL di browser.
     * Menggunakan try-catch untuk keamanan jika tidak ada browser.
     */
    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Tidak ada browser terpasang untuk membuka link.", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Fungsi untuk menampilkan menu popup (Bantuan, Hubungi Kami, Logout).
     */
    private fun showOverflowMenu(view: android.view.View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.overflow_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.menu_bantuan -> {
                    startActivity(Intent(this, BantuanActivity::class.java))
                    true
                }
                R.id.menu_hubungi_kami -> {
                    startActivity(Intent(this, HubungiKamiActivity::class.java))
                    true
                }
                R.id.menu_logout -> {
                    // Logout - kembali ke MainActivity (welcome screen)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish() // tutup HomeActivity agar user tidak bisa back ke sini
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
        }
}