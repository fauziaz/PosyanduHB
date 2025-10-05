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
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences.getInstance(this)

        // Load existing data
        binding.etUsername.setText(userPreferences.getUsername())
        binding.etEmail.setText(userPreferences.getEmail())
        binding.tvDob.text = userPreferences.getDob()
        binding.etPhone.setText(userPreferences.getPhone())

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
            // Save profile data
            userPreferences.saveUserProfile(
                username = binding.etUsername.text.toString(),
                email = binding.etEmail.text.toString(),
                dob = binding.tvDob.text.toString(),
                phone = binding.etPhone.text.toString()
            )
            
            Toast.makeText(this, "Profile berhasil disimpan", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        }
        // wire bottom nav/fab
        setupBottomNavigation(this)
    }
}
