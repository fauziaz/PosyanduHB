package com.example.posyanduhb

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posyanduhb.databinding.ActivityJadwalBinding
import com.example.posyanduhb.databinding.LayoutEventItemBinding
import com.example.posyanduhb.databinding.LayoutJadwalBinding

data class Event(val name: String, val time: String, val location: String)
data class DaySchedule(val day: String, val events: List<Event>)

class JadwalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJadwalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        val jadwalList = listOf(
            DaySchedule(
                "Rabu, 17 April 2025", listOf(
                    Event("Imunisasi Anak", "09:00 – 13:00", "Aula Kantor Lurah"),
                    Event("Penimbangan Berat Badan", "13:00 – 14:00", "Aula Kantor Lurah"),
                    Event("Pengukuran Tinggi Badan", "14:00 – 15:00", "Aula Kantor Lurah")
                )
            ),
            DaySchedule(
                "Jumat, 19 April 2025", listOf(
                    Event("Penimbangan Berat Badan", "09:00 – 10:00", "Aula Kantor Lurah"),
                    Event("Pengukuran Tinggi Badan", "10:00 – 11:00", "Aula Kantor Lurah")
                )
            )
        )

        // Set up RecyclerView
        binding.recyclerJadwal.layoutManager = LinearLayoutManager(this)
        binding.recyclerJadwal.adapter = JadwalAdapter(jadwalList)

        // back button support: finish this activity when back icon is clicked
        try {
            binding.btnBack.setOnClickListener { finish() }
        } catch (e: Exception) {
            val back = findViewById<android.view.View>(R.id.btnBack)
            back?.setOnClickListener { finish() }
        }
    }
}

class JadwalAdapter(private val days: List<DaySchedule>) :
    RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutJadwalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutJadwalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = days.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val daySchedule = days[position]
        holder.binding.tvHariTanggal.text = daySchedule.day
        holder.binding.containerEvents.removeAllViews()

        for (event in daySchedule.events) {
            val eventView = LayoutEventItemBinding.inflate(
                LayoutInflater.from(holder.binding.root.context),
                holder.binding.containerEvents,
                false
            )
            eventView.tvNamaEvent.text = event.name
            eventView.tvWaktu.text = event.time
            eventView.tvLokasi.text = "• ${event.location}"
            holder.binding.containerEvents.addView(eventView.root)
        }
    }
}
