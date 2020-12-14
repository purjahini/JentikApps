package net.bedev.jentikapps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_statistik.view.*
import net.bedev.jentikapps.R
import net.bedev.jentikapps.model.get_jentik


class StatistikAdapter(val listStatistik: List<get_jentik.Data>) :
    RecyclerView.Adapter<MvpHolder>() {
    private lateinit var context: Context
    var nilaiP1 = 0
    var nilaiP2 = 0
    var nilaiP3 = 0
    var nilaiP4 = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvpHolder {
        context = parent.context
        return MvpHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_statistik,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listStatistik.size


    override fun onBindViewHolder(holder: MvpHolder, position: Int) {
        val data = listStatistik[position]
        val nama = holder.itemView.TvNama
        nama.text = "Nama : " + data.nama_lengkap
        val dusun = holder.itemView.TvDusun
        dusun.text = "Dusun : " + data.dusun
        val alamat = holder.itemView.TvAlamat
        alamat.text = "Alamat : " + data.alamat
        val rt = holder.itemView.TvRt
        rt.text = "RT : " + data.rt
        val rw = holder.itemView.TvRw
        rw.text = "RW : " + data.rw
        val bakmandi = data.bak_mandi
        val penampungan_air = data.penampungan_air
        val saluran_irigasi = data.saluran_irigasi
        val tempat_sampah = data.tempat_sampah

        if (bakmandi == 1) {
            nilaiP1 = 25
            val TvBakMandi = holder.itemView.TvBakMandi
            TvBakMandi.text = "Area Bak Mandi : Ya, Ada Jentik "

        } else {
            nilaiP1 = 0
            val TvBakMandi = holder.itemView.TvBakMandi
            TvBakMandi.text = "Area Bak Mandi : Tidak,Jentik Nihil "
        }
        if (penampungan_air == 1) {
            nilaiP2 = 25
            val TvPenampungan = holder.itemView.TvTampunganAir
            TvPenampungan.text = "Area Penampungan : Ya, Ada Jentik "

        } else {
            nilaiP2 = 0
            val TvPenampungan = holder.itemView.TvTampunganAir
            TvPenampungan.text = "Area Penampungan :Tidak,Jentik Nihil "
        }
        if (saluran_irigasi == 1) {
            nilaiP3 = 25
            val SaluranIrigasi = holder.itemView.TvIrigasi
            SaluranIrigasi.text = "Area Saluran Irigasi : Ya, Ada Jentik "

        } else {
            nilaiP3 = 0
            val SaluranIrigasi = holder.itemView.TvIrigasi
            SaluranIrigasi.text = "Area Saluran Irigasi : Tidak,Jentik Nihil "

        }
        if (tempat_sampah == 1) {
            nilaiP4 = 25
            val tempatSampah = holder.itemView.TvTempatSampah
            tempatSampah.text = "Area Tempat Sampah : Ya, Ada Jentik "
        } else {
            nilaiP4 = 0
            val tempatSampah = holder.itemView.TvTempatSampah
            tempatSampah.text = "Area Tempat Sampah : Tidak,Jentik Nihil"

        }
        val jumlah = nilaiP1 + nilaiP2 + nilaiP3 + nilaiP4
        when (jumlah) {
            0 -> {
                val hasil = holder.itemView.TvResult

                hasil.text = "Keadaan Rumah Aman Dari Nyamuk DBD"
                hasil.setTextColor(ContextCompat.getColor(context, R.color.hijau))

            }
            25 -> {
                val hasil = holder.itemView.TvResult
                hasil.text = "Keadaan Rumah Kurang Aman Dari Nyamuk DBD"
                hasil.setTextColor(ContextCompat.getColor(context, R.color.kuning))
            }
            50 -> {
                val hasil = holder.itemView.TvResult
                hasil.text = "Keadaan Rumah Cukup Aman Dari Nyamuk DBD"
                hasil.setTextColor(ContextCompat.getColor(context, R.color.biru))

            }
            75 -> {
                val hasil = holder.itemView.TvResult
                hasil.text = "Keadaan Rumah Rawan Dari Nyamuk DBD"
                hasil.setTextColor(ContextCompat.getColor(context, R.color.merah))
            }
            100 -> {
                val hasil = holder.itemView.TvResult
                hasil.text = "Keadaan Rumah Berbahaya Dari Nyamuk DBD"
                hasil.setTextColor(ContextCompat.getColor(context, R.color.hitam))
            }
        }


    }
}