package com.example.posyanduhb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        val tvJudul = view.findViewById<android.widget.TextView>(R.id.tv_judul_riwayat)
        val tvSubjudul = view.findViewById<android.widget.TextView>(R.id.tv_subjudul_riwayat)
        val tvKeterangan = view.findViewById<android.widget.TextView>(R.id.tv_keterangan_kanan)
        val ivIcon = view.findViewById<android.widget.ImageView>(R.id.icon_riwayat)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat) // pastikan namanya activity_riwayat.xml

        // Tombol Back
        findViewById<android.widget.ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Data dummy Imunisasi
        val imunisasiData = listOf(
            Riwayat("DTP", "12 Maret 2025", "Dosis 1", R.drawable.ic_suntikan),
            Riwayat("MMR", "2 Februari 2025", "Dosis 2", R.drawable.ic_suntikan),
            Riwayat("Polio", "10 Januari 2025", "Dosis 1", R.drawable.ic_suntikan)
        )

        // Data dummy Penimbangan
        val penimbanganData = listOf(
            Riwayat("8 kg", "12 Maret 2025", "+ 0.2 kg", R.drawable.ic_timbangan),
            Riwayat("7.8 kg", "2 Februari 2025", "+ 0.6 kg", R.drawable.ic_timbangan),
            Riwayat("7.2 kg", "10 Januari 2025", "sehat", R.drawable.ic_timbangan)
        )

        // Data dummy Pengukuran
        val pengukuranData = listOf(
            Riwayat("68 cm", "12 Maret 2025", "70 cm", R.drawable.ic_penggaris),
            Riwayat("66 cm", "2 Februari 2025", "75 cm", R.drawable.ic_penggaris),
            Riwayat("60 cm", "10 Januari 2025", "75 cm", R.drawable.ic_penggaris)

        )

        // Set RecyclerViews
        val rvImunisasi = findViewById<RecyclerView>(R.id.rvImunisasi)
        rvImunisasi.layoutManager = LinearLayoutManager(this)
        rvImunisasi.adapter = RiwayatAdapter(imunisasiData)

        val rvPenimbangan = findViewById<RecyclerView>(R.id.rvPenimbangan)
        rvPenimbangan.layoutManager = LinearLayoutManager(this)
        rvPenimbangan.adapter = RiwayatAdapter(penimbanganData)

        val rvPengukuran = findViewById<RecyclerView>(R.id.rvPengukuran)
        rvPengukuran.layoutManager = LinearLayoutManager(this)
        rvPengukuran.adapter = RiwayatAdapter(pengukuranData)
    }
}
