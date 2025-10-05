package com.example.posyanduhb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<EditText>(R.id.etRegUsername)
        val etEmail = findViewById<EditText>(R.id.etRegEmail)
        val etPassword = findViewById<EditText>(R.id.etRegPassword)
    val etDob = findViewById<EditText>(R.id.etRegDob)
        val cbAgree = findViewById<CheckBox>(R.id.cbAgree)
    val btnSignUp = findViewById<Button>(R.id.btnSignUp)
    val tvHaveAccount = findViewById<TextView>(R.id.tvHaveAccount)

    // underline the "have account" link so it looks clickable
    tvHaveAccount.paint.isUnderlineText = true

        // show date picker when tapping the dob field
        etDob.setOnClickListener {
            val c = java.util.Calendar.getInstance()
            val year = c.get(java.util.Calendar.YEAR)
            val month = c.get(java.util.Calendar.MONTH)
            val day = c.get(java.util.Calendar.DAY_OF_MONTH)
            val dpd = android.app.DatePickerDialog(this, { _, y, m, d ->
                // m is 0-based
                val mm = m + 1
                val dd = if (d < 10) "0$d" else "$d"
                val mms = if (mm < 10) "0$mm" else "$mm"
                etDob.setText("$dd/$mms/$y")
            }, year, month, day)
            dpd.show()
        }

        btnSignUp.setOnClickListener {
            val u = etUsername.text.toString().trim()
            val e = etEmail.text.toString().trim()
            val p = etPassword.text.toString().trim()
            val d = etDob.text.toString().trim()
            if (u.isEmpty() || e.isEmpty() || p.isEmpty() || d.isEmpty()) {
                Toast.makeText(this, "Lengkapi semua field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!cbAgree.isChecked) {
                Toast.makeText(this, "Setujui kebijakan untuk melanjutkan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // For demo, after sign up go to Login
            Toast.makeText(this, "Akun dibuat. Silakan login.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        tvHaveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
