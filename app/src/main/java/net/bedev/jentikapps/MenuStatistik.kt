package net.bedev.jentikapps

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_menu_statistik.*
import net.bedev.jentikapps.adapter.StatistikAdapter
import net.bedev.jentikapps.helper.Cons
import net.bedev.jentikapps.helper.See
import net.bedev.jentikapps.model.get_jentiks
import net.bedev.jentikapps.rest.ApiConfig
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuStatistik : AppCompatActivity() {
    var id_kader = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_statistik)
        var sharedPreferences = getSharedPreferences("kader", Context.MODE_PRIVATE)
        id_kader = sharedPreferences.getString("id_kader", "").toString()
        See.log("id_kader : $id_kader")


        getStatistik()
    }

    private fun getStatistik() {
        RvStatistik.layoutManager = LinearLayoutManager(applicationContext)
        val apiService = ApiConfig.getApiService()
        apiService.getJentik()
            .enqueue(object : Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    See.toast(this@MenuStatistik, "Cek Koneksi internet anda")
                    PbLoadingStatistik.visibility = View.GONE
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val respon = response.body()?.string()
                    val json = JSONObject(respon)
                    val apiStatus = json.getInt(Cons.api_status)

                    if (apiStatus == 1 ){
                        var data =Gson().fromJson(respon, get_jentiks::class.java)
                        val list = data.data

                        PbLoadingStatistik.visibility = View.GONE
                        RvStatistik.adapter = StatistikAdapter(list)
                    }else {
                        See.log("listBalita Adapter kosong")

                    }
                }

            })

    }
}
