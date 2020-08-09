package net.bedev.jentikapps.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_menu_statistik.view.*
import kotlinx.android.synthetic.main.item_statistik.view.*
import net.bedev.jentikapps.R
import net.bedev.jentikapps.model.get_jentik

class StatistikAdapter (val listStatistik : List<get_jentik.Data>) : RecyclerView.Adapter<MvpHolder>() {
    private lateinit var context: Context
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
        var nama = holder.itemView.TvNama
        nama.text = "Nama : "+data.nama_lengkap





        var alamat = holder.itemView.TvAlamat
        alamat.text = "Alamat : "+data.alamat
        var rt = holder.itemView.TvRt
        rt.text = "RT :"+data.rt
        var rw = holder.itemView.TvRw
        rw.text = "RW :"+data.rw
        var bakmandi = data.bak_mandi
        var penampungan_air = data.penampungan_air
        var saluran_irigasi = data.saluran_irigasi
        if (bakmandi == 1){
            val TvBakMandi = holder.itemView.TvBakMandi
            TvBakMandi.text = "Area Bak Mandi : Ya, Ada Jentik "

        } else {
            val TvBakMandi = holder.itemView.TvBakMandi
            TvBakMandi.text = "Area Bak Mandi : Tidak,Jentik Nihil "
        }
        if (penampungan_air == 1){
            val TvPenampungan = holder.itemView.TvTampunganAir
            TvPenampungan.text = "Area Penampungan : Ya, Ada Jentik "

        }else {
            val TvPenampungan = holder.itemView.TvTampunganAir
            TvPenampungan.text = "Area Penampungan :Tidak,Jentik Nihil "
        }
         if (saluran_irigasi == 1){
             val SaluranIrigasi = holder.itemView.TvIrigasi
             SaluranIrigasi.text = "Area Saluran Irigasi : Ya, Ada Jentik "

         }else {
             val SaluranIrigasi = holder.itemView.TvIrigasi
             SaluranIrigasi.text = "Area Saluran Irigasi : Tidak,Jentik Nihil "

         }

    }
}