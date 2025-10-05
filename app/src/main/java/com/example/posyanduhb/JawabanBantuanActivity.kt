package com.example.posyanduhb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posyanduhb.databinding.ActivityJawabanBantuanBinding

class JawabanBantuanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJawabanBantuanBinding

    companion object {
        const val EXTRA_PERTANYAAN = "extra_pertanyaan"
        const val EXTRA_JAWABAN = "extra_jawaban"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJawabanBantuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data yang dikirim dari BantuanActivity
        val pertanyaan = intent.getStringExtra(EXTRA_PERTANYAAN)
        val jawaban = intent.getStringExtra(EXTRA_JAWABAN)

        // Menampilkan data ke TextViews
        binding.tvPertanyaan.text = pertanyaan
        binding.tvJawaban.text = jawaban

        // Fungsi tombol kembali
        binding.ivBackButton.setOnClickListener {
            finish()
        }
    }
}
