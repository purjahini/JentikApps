package net.bedev.jentikapps

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_ab_jentik.*
import net.bedev.jentikapps.helper.Cons
import net.bedev.jentikapps.helper.See
import net.bedev.jentikapps.model.get_abj
import net.bedev.jentikapps.rest.ApiConfig
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AbJentikActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ab_jentik)

        val allRt = arrayOf("002","003","004")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, allRt)

        SpRtAjb.adapter = arrayAdapter

        SpRtAjb.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pbLoadingAbj.visibility = View.VISIBLE
                val rt = allRt[position]
                val apiService = ApiConfig.getApiService()
                apiService.GetAbj(rt.trim())
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            pbLoadingAbj.visibility = View.GONE
                            val respon = response.body()?.string()
                            val json = JSONObject(respon)
                            val apiStatus = json.getInt(Cons.api_status)
                            if (apiStatus == 1) {
                                var data = Gson().fromJson(respon, get_abj::class.java).data
                                var jumlahJentik = ""
                                var jumRumah = ""
                                data.map {
                                    jumRumah = it.jmlRumah
                                    jumlahJentik = it.jmlJentikRmh

                                    TvJumlahRumahJentik.text = "Jumlah Rumah (+) Jentik : "+jumlahJentik
                                    TvJumlahRumahPeriksa.text = "Jumlah Rumah Diperiksa : "+jumRumah
                                }
                                val bagi = jumlahJentik.toFloat() / jumRumah.toFloat()

                                val  houseIndex = bagi*100

                                TvHouseIndex.text = "Hasil Nilai House Index : "+houseIndex+" %"

                                val abj = 100 - houseIndex
                                TvAbj.text = "Hasil Nilai Angka Bebas Jentik : $abj %"

                                if (abj < 94) {
                                    TvResult.text = "Keadaan Wilayah Kurang Sehat dari jentik"
                                    TvResult.setTextColor(
                                        ContextCompat.getColor(
                                            this@AbJentikActivity,
                                            R.color.merah
                                        )
                                    )
                                } else {
                                    TvResult.text = "Keadaan Wilayah aman dari jentik"
                                    TvResult.setTextColor(
                                        ContextCompat.getColor(
                                            this@AbJentikActivity,
                                            R.color.hijau
                                        )
                                    )

                                }


                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            pbLoadingAbj.visibility = View.GONE
                            See.log("onFailure")
                            See.toast(this@AbJentikActivity, "Cek Koneksi Internet anda")
                        }

                    })

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

}