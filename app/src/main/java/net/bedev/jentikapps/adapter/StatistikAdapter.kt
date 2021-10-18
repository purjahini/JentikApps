package net.bedev.jentikapps.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_statistik.view.*
import net.bedev.jentikapps.Dashboard
import net.bedev.jentikapps.R
import net.bedev.jentikapps.helper.Cons
import net.bedev.jentikapps.helper.See
import net.bedev.jentikapps.model.get_jentiks
import net.bedev.jentikapps.rest.ApiConfig
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatistikAdapter(val listStatistik: List<get_jentiks.Data>) :
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

        nama.text = "Nama : " + data.namaLengkap
        val dusun = holder.itemView.TvDusun
        dusun.text = "Dusun : " + data.dusun
        val alamat = holder.itemView.TvAlamat
        alamat.text = "Alamat : " + data.alamat
        val rt = holder.itemView.TvRt
        rt.text = "RT : " + data.rt
        val rw = holder.itemView.TvRw
        rw.text = "RW : " + data.rw
        val bakmandi = data.bakMandi
        val penampungan_air = data.penampunganAir
        val saluran_irigasi = data.saluranIrigasi
        val tempat_sampah = data.tempatSampah
        val tempayanItem = data.tempayan
        val pecahanBotol = data.pecahanBotol
        val kulkas = data.kulkas
        val dispenser = data.dispenser
        val tandonAir = data.tandonAir
        val vasBunga = data.vasBunga
        val potBunga = data.potBunga

        val btnHps = holder.itemView.btnHapus
        val pbload = holder.itemView.pbDetail
        val idJentik = data.idPeriksajentik
        btnHps.setOnClickListener {
            pbload.visibility = View.VISIBLE
            val apiService = ApiConfig.getApiService()
            apiService.DeleteJentik(idJentik.trim())
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        pbload.visibility = View.GONE
                       val respon = response.body()?.string()
                        val json = JSONObject(respon)
                        val apiStatus = json.getInt(Cons.api_status)
                        if (apiStatus == 1) {
                            See.toast(context,"Hapus data berhasil.")
                            val intent = Intent(context, Dashboard::class.java)
                            context.startActivity(intent)


                        }
                        else {
                            See.toast(context,"Hapus data gagal.")
                        }

                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        pbload.visibility = View.GONE
                        See.toast(context,"mohon check koneksi internet anda.")

                    }

                })

        }

        if (bakmandi.toInt() == 1) {
            nilaiP1 = 25
            val TvBakMandi = holder.itemView.TvBakMandi
            TvBakMandi.text = "Area Bak Mandi : Ya, Ada Jentik "

        } else {
            nilaiP1 = 0
            val TvBakMandi = holder.itemView.TvBakMandi
            TvBakMandi.text = "Area Bak Mandi : Tidak,Jentik Nihil "
        }
        if (penampungan_air.toInt() == 1) {
            nilaiP2 = 25
            val TvPenampungan = holder.itemView.TvTampunganAir
            TvPenampungan.text = "Area Penampungan : Ya, Ada Jentik "

        } else {
            nilaiP2 = 0
            val TvPenampungan = holder.itemView.TvTampunganAir
            TvPenampungan.text = "Area Penampungan :Tidak,Jentik Nihil "
        }
        if (saluran_irigasi.toInt() == 1) {
            nilaiP3 = 25
            val SaluranIrigasi = holder.itemView.TvIrigasi
            SaluranIrigasi.text = "Area Saluran Irigasi : Ya, Ada Jentik "

        } else {
            nilaiP3 = 0
            val SaluranIrigasi = holder.itemView.TvIrigasi
            SaluranIrigasi.text = "Area Saluran Irigasi : Tidak,Jentik Nihil "

        }

        if (tempat_sampah.toInt() == 1) {
            nilaiP4 = 25
            val tempatSampah = holder.itemView.TvTempatSampah
            tempatSampah.text = "Area Tempat Sampah : Ya, Ada Jentik "
        } else {
            nilaiP4 = 0
            val tempatSampah = holder.itemView.TvTempatSampah
            tempatSampah.text = "Area Tempat Sampah : Tidak,Jentik Nihil"
        }

        if (tempayanItem.equals("1") ) {
            val tvtempayan = holder.itemView.Tvtempayan
            tvtempayan.text = "Area Tempayan : Ya, Ada Jentik "
        } else {
            val tvtempayan = holder.itemView.Tvtempayan
            tvtempayan.text = "Area Tempayan : Tidak,Jentik Nihil"
        }

        if (pecahanBotol.equals("1")) {
            val tvpecahanbotol = holder.itemView.Tvpecahanbotol
            tvpecahanbotol.text = "Area Pecahan Botol : Ya, Ada Jentik "
        } else {
            val tvpecahanbotol = holder.itemView.Tvpecahanbotol
            tvpecahanbotol.text = "Area Pecahan Botol : Tidak,Jentik Nihil"
        }

        if (kulkas.equals("1")) {
            val tvkulkas = holder.itemView.Tvkulkas
            tvkulkas.text = "Area Kulkas : Ya, Ada Jentik "
        } else {
            val tvkulkas = holder.itemView.Tvkulkas
            tvkulkas.text = "Area Kulkas : Tidak,Jentik Nihil"
        }
        if (dispenser.equals("1")) {
            val tvdispenser = holder.itemView.Tvdispenser
            tvdispenser.text = "Area Dispenser : Ya, Ada Jentik "
        } else {
            val tvdispenser = holder.itemView.Tvdispenser
            tvdispenser.text = "Area Dispenser : Tidak,Jentik Nihil"
        }

        if (tandonAir.equals("1")) {
            val tvtandonair = holder.itemView.Tvtandonair
            tvtandonair.text = "Area Tandon Air : Ya, Ada Jentik "
        } else {
            val tvtandonair = holder.itemView.Tvtandonair
            tvtandonair.text = "Area Tandon Air : Tidak,Jentik Nihil"
        }
        if (vasBunga.equals("1")) {
            val tvvasbunga = holder.itemView.Tvvasbunga
            tvvasbunga.text = "Area Vas Bunga : Ya, Ada Jentik "
        } else {
            val tvvasbunga = holder.itemView.Tvvasbunga
            tvvasbunga.text = "Area Vas Bunga : Tidak,Jentik Nihil"
        }
        if (potBunga.equals("1")) {
            val tvpotbunga = holder.itemView.Tvpotbunga
            tvpotbunga.text = "Area Pot Bunga : Ya, Ada Jentik "
        } else {
            val tvpotbunga = holder.itemView.Tvpotbunga
            tvpotbunga.text = "Area Pot Bunga : Tidak,Jentik Nihil"
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