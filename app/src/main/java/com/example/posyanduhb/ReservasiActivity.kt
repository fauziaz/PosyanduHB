package com.example.posyanduhb

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ReservasiActivity : AppCompatActivity() {
    
    private lateinit var userPreferences: UserPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservasi)

        // Initialize UserPreferences
        userPreferences = UserPreferences.getInstance(this)
        
        // Update greeting dengan username
        updateUserGreeting()

        // Deklarasi View
        val spinnerLayanan = findViewById<Spinner>(R.id.spinnerLayanan)
        val etTanggal = findViewById<EditText>(R.id.etTanggal)
        val spinnerJam = findViewById<Spinner>(R.id.spinnerJam)
        val etNamaBalita = findViewById<EditText>(R.id.etNamaBalita)
        val tvNomorAntrian = findViewById<TextView>(R.id.tvNomorAntrian)
        val btnKonfirmasi = findViewById<Button>(R.id.btnKonfirmasi)

        // Setup Spinner dengan custom layout
        setupSpinners(spinnerLayanan, spinnerJam)

        // Date picker untuk tanggal
        etTanggal.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, y, m, d ->
                etTanggal.setText("$d/${m + 1}/$y")
            }, year, month, day)

            datePicker.show()
        }

        // Event tombol konfirmasi
        btnKonfirmasi.setOnClickListener {
            val namaBalita = etNamaBalita.text.toString().trim()
            val layanan = spinnerLayanan.selectedItem.toString()
            val tanggal = etTanggal.text.toString()
            val jam = spinnerJam.selectedItem.toString()
            val nomorAntrian = "A12" // sementara static, nanti bisa generate otomatis

            if (namaBalita.isEmpty()) {
                etNamaBalita.error = "Nama balita tidak boleh kosong"
                Toast.makeText(this, "Silakan isi nama balita terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            } else if (tanggal.isEmpty()) {
                Toast.makeText(this, "Silakan pilih tanggal terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            } else if (layanan == "Pilih Layanan") {
                Toast.makeText(this, "Silakan pilih layanan terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            } else if (jam == "Pilih Jam Antrian") {
                Toast.makeText(this, "Silakan pilih jam antrian terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // tampilkan nomor antrian
                tvNomorAntrian.text = nomorAntrian

                // Kirim data ke halaman berhasil
                val intent = Intent(this, ReservasiBerhasilActivity::class.java)
                intent.putExtra("namaBalita", namaBalita)
                intent.putExtra("layanan", layanan)
                intent.putExtra("tanggal", tanggal)
                intent.putExtra("jam", jam)
                intent.putExtra("nomorAntrian", nomorAntrian)
                startActivity(intent)
            }
        }
        setupBottomNavigation(this)
    }

    private fun setupSpinners(spinnerLayanan: Spinner, spinnerJam: Spinner) {
        // Isi pilihan layanan dengan default prompt
        val layananOptions = arrayOf(
            "Pilih Layanan",
            "Imunisasi Balita",
            "Pemeriksaan Ibu Hamil",
            "Pemeriksaan Lansia",
            "Konsultasi Gizi"
        )
        val layananAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, layananOptions)
        layananAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLayanan.adapter = layananAdapter

        // Isi pilihan jam dengan default prompt
        val jamOptions = arrayOf(
            "Pilih Jam Antrian",
            "08.00 - 09.00",
            "09.00 - 10.00",
            "10.00 - 11.00",
            "11.00 - 12.00"
        )
        val jamAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, jamOptions)
        jamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJam.adapter = jamAdapter
    }

    /**
     * Update greeting dengan username dari SharedPreferences
     */
    private fun updateUserGreeting() {
        val username = userPreferences.getUsername()
        if (username.isNotEmpty()) {
            val tvGreeting = findViewById<TextView>(R.id.tv_greeting)
            tvGreeting?.text = "Hallo,\n$username"
        }
    }
}
