package com.example.posyanduhb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posyanduhb.databinding.ActivityBantuanBinding

class BantuanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBantuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBantuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menambahkan listener klik pada tombol kembali
        binding.ivBackButton.setOnClickListener {
            // Menutup activity saat ini dan kembali ke halaman sebelumnya
            finish()
        }
    }
}
