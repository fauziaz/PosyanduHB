package com.example.posyanduhb

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.posyanduhb.databinding.ActivityProfileBinding
import java.text.SimpleDateFormat
import java.util.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        // date picker for DOB
        binding.ivCalendar.setOnClickListener {
            val cal = Calendar.getInstance()
            val dp = DatePickerDialog(this, { _, y, m, d ->
                val sdf = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id"))
                cal.set(y, m, d)
                binding.tvDob.text = sdf.format(cal.time)
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            dp.show()
        }

        binding.btnSave.setOnClickListener {
            Toast.makeText(this, "Profile saved (mock)", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
