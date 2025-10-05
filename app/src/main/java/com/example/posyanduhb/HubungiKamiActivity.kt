package com.example.posyanduhb

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.posyanduhb.databinding.ActivityHubungiKamiBinding

class HubungiKamiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHubungiKamiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHubungiKamiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back
        binding.btnBack.setOnClickListener { finish() }

        // WhatsApp button: open wa.me link
        binding.btnWhatsApp.setOnClickListener {
            val phone = binding.tvWhatsAppNumber.text.toString().replace("+", "")
            val url = "https://wa.me/$phone"
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak dapat membuka WhatsApp", Toast.LENGTH_SHORT).show()
            }
        }

        // Email click: open email client
        binding.tvEmail.setOnClickListener {
            val email = binding.tvEmail.text.toString()
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak ada aplikasi email", Toast.LENGTH_SHORT).show()
            }
        }

        // Map thumbnail: open location in maps
        binding.mapThumb.setOnClickListener {
            val address = binding.tvAddress.text.toString()
            val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address))
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            try {
                startActivity(mapIntent)
            } catch (e: ActivityNotFoundException) {
                // fallback to browser
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(address)))
                startActivity(browserIntent)
            }
        }

        // Kirim Pesan -> send as email to admin with form fields
        binding.btnKirimPesan.setOnClickListener {
            val name = binding.etNama.text.toString().trim()
            val contact = binding.etEmailOrPhone.text.toString().trim()
            val pesan = binding.etPesan.text.toString().trim()

            if (name.isEmpty() || pesan.isEmpty()) {
                Toast.makeText(this, "Isi Nama dan Pesan terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val subject = "Pesan dari $name"
            val body = "Nama: $name\nKontak: $contact\n\nPesan:\n$pesan"

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:posyanduharapanbunda@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Tidak ada aplikasi email untuk mengirim pesan", Toast.LENGTH_SHORT).show()
            }
        }

        // Use shared bottom navigation wiring (setupBottomNavigation will attach listeners)
        setupBottomNavigation(this)
    }
}
