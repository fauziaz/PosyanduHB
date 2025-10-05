package com.example.posyanduhb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posyanduhb.databinding.ActivityBantuanBinding

class BantuanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBantuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBantuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fungsi tombol kembali
        binding.ivBackButton.setOnClickListener {
            finish()
        }

        // --- Logika untuk setiap item pertanyaan ---

        // Pastikan Anda sudah menambahkan android:id di file activity_bantuan.xml
        // Contoh: android:id="@+id/card_pertanyaan_1" untuk CardView pertama, dst.

        binding.cardPertanyaan1.setOnClickListener {
            val pertanyaan = "Bagaimana cara memesan (reservasi) melalui aplikasi?"
            val jawaban = "Untuk melakukan reservasi online, Anda bisa mengikuti langkah-langkah berikut:\n\n" +
                    "• Pada halaman utama (Beranda), tekan tombol 'Reservasi Online' atau tombol '+' Reservasi di bagian bawah layar.\n" +
                    "• Anda akan diarahkan ke halaman reservasi.\n" +
                    "• Pilih Layanan yang Anda butuhkan (contoh: imunisasi Balita).\n" +
                    "• Pilih Tanggal dan Jam Antrian yang tersedia.\n" +
                    "• Masukkan Nama Balita Anda, lalu tekan tombol 'Konfirmasi Reservasi'.\n" +
                    "• Reservasi Anda berhasil dan Anda akan mendapatkan nomor antrian."

            openJawabanActivity(pertanyaan, jawaban)
        }

        binding.cardPertanyaan2.setOnClickListener {
            val pertanyaan = "Bagaimana melihat jadwal pemeriksaan yang ada di Posyandu Harapan Bunda?"
            val jawaban = "Anda dapat melihat jadwal lengkap pelayanan Posyandu dengan menekan tombol 'Jadwal Pemeriksaan' pada halaman utama (Beranda). " +
                    "Di sana akan ditampilkan semua jadwal kegiatan yang akan datang, lengkap dengan hari, tanggal, jam, dan lokasi pelayanan."

            openJawabanActivity(pertanyaan, jawaban)
        }

        binding.cardPertanyaan3.setOnClickListener {
            val pertanyaan = "Apakah bisa berkonsultasi dengan dokter terkait tumbuh kembang balita?"
            val jawaban = "Bisa. Posyandu Harapan Bunda menyediakan layanan konsultasi gizi dan kesehatan, seperti yang tertera pada bagian 'Pelayanan Kami'.\n\n" +
                    "Untuk detail lebih lanjut mengenai jadwal konsultasi, Anda dapat menghubungi kami melalui kontak yang ada di menu 'Hubungi Kami'. " +
                    "Selain itu, aplikasi ini juga menyediakan artikel 'Tumbuh Kembang Balita' di halaman Beranda untuk menambah wawasan Anda."

            openJawabanActivity(pertanyaan, jawaban)
        }

        // Tambahkan listener untuk pertanyaan lain dengan cara yang sama
        // Pastikan ID CardView-nya sesuai
        binding.cardPertanyaan4.setOnClickListener {
            val pertanyaan = "Dimana bisa melihat hasil reservasi online yang sudah berhasil?"
            val jawaban = "Setelah reservasi berhasil, Anda akan mendapatkan nomor antrian. Anda juga dapat melihat riwayat reservasi Anda di halaman 'Profil Akun' pada bagian 'Riwayat Reservasi'."
            openJawabanActivity(pertanyaan, jawaban)
        }

    }

    /**
     * Fungsi untuk membuka JawabanBantuanActivity dan mengirim data.
     * @param pertanyaan Teks pertanyaan yang akan dikirim.
     * @param jawaban Teks jawaban yang akan dikirim.
     */
    private fun openJawabanActivity(pertanyaan: String, jawaban: String) {
        val intent = Intent(this, JawabanBantuanActivity::class.java).apply {
            putExtra(JawabanBantuanActivity.EXTRA_PERTANYAAN, pertanyaan)
            putExtra(JawabanBantuanActivity.EXTRA_JAWABAN, jawaban)
        }
        startActivity(intent)
    }
}


