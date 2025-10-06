package com.example.posyanduhb

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduhb.databinding.ActivityRiwayatBinding

// Model Riwayat
data class Riwayat(
    val judul: String,
    val subjudul: String,
    val keterangan: String,
    val iconRes: Int
)

// Adapter RecyclerView
class RiwayatAdapter(private val list: List<Riwayat>) :
    RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    inner class ViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val tvJudul: TextView = view.findViewById(R.id.tv_judul_riwayat)
        val tvSubjudul: TextView = view.findViewById(R.id.tv_subjudul_riwayat)
        val tvKeterangan: TextView = view.findViewById(R.id.tv_keterangan_kanan)
        val ivIcon: ImageView = view.findViewById(R.id.icon_riwayat)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_card_riwayat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvJudul.text = item.judul
        holder.tvSubjudul.text = item.subjudul
        holder.tvKeterangan.text = item.keterangan
        holder.ivIcon.setImageResource(item.iconRes)
    }
}

// Activity
class RiwayatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize UserPreferences
        userPreferences = UserPreferences.getInstance(this)

        // Setup UI
        setupUI()
        
        // Setup bottom navigation
        setupBottomNavigation(this)
    }

    private fun setupUI() {
        // Update username dari SharedPreferences
        val username = userPreferences.getUsername()
        if (username.isNotEmpty()) {
            binding.tvUsernameRiwayat.text = username
        }
        
        // Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Data dummy dengan icon yang pasti ada
        val imunisasiData = listOf(
            Riwayat("DPT", "12 Maret 2025", "Selesai", R.drawable.ic_suntikan),
            Riwayat("MMR", "2 Februari 2025", "Selesai", R.drawable.ic_suntikan),
            Riwayat("Polio", "10 Januari 2025", "Selesai", R.drawable.ic_suntikan)
        )

        val penimbanganData = listOf(
            Riwayat("8.2kg", "12 Maret 2025", "Normal", R.drawable.ic_timbangan),
            Riwayat("7.8kg", "2 Februari 2025", "Normal", R.drawable.ic_timbangan),
            Riwayat("7.2kg", "10 Januari 2025", "Normal", R.drawable.ic_timbangan)
        )

        val pengukuranData = listOf(
            Riwayat("68cm", "12 Maret 2025", "Normal", R.drawable.ic_penggaris),
            Riwayat("66cm", "2 Februari 2025", "Normal", R.drawable.ic_penggaris),
            Riwayat("60cm", "10 Januari 2025", "Normal", R.drawable.ic_penggaris)
        )

        // Setup RecyclerViews dengan binding
        setupRecyclerView(binding.rvImunisasi, imunisasiData)
        setupRecyclerView(binding.rvPenimbangan, penimbanganData)
        setupRecyclerView(binding.rvPengukuran, pengukuranData)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, data: List<Riwayat>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RiwayatAdapter(data)
    }
}
