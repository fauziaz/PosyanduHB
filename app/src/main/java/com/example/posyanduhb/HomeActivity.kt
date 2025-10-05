package com.example.posyanduhb
import android.net.Uri
                }
            }
        }
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
<<<<<<< HEAD
        binding.cardArtikelMpasi.setOnClickListener {
            val url = "https://www.rspondokindah.co.id/id/news/menyiapkan-asupan-pertama-untuk-si-kecil"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
=======

            val p = findViewById<android.view.View>(R.id.img_profil_akun)
            p?.setOnClickListener {
                startActivity(android.content.Intent(this, ProfileActivity::class.java))
            }
        }

        // bottom nav: handle profile item click
        try {
            binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_profile -> {
                        startActivity(android.content.Intent(this, ProfileActivity::class.java))
                        true
                    }
                    else -> false
                }

            }
        } catch (e: Exception) {
            val bn = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation_view)
            bn?.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_profile -> {
                        startActivity(android.content.Intent(this, ProfileActivity::class.java))
                        true
                    }
                    else -> false
                }
            }
>>>>>>> 9d455b0 (halaman hubungi kami, profil dan bantuan)
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
                        // Aksi saat item "Hubungi Kami" diklik
                        val intent = Intent(this, HubungiKamiActivity::class.java)
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

