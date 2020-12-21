package net.bedev.jentikapps

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_input_jentik.*
import net.bedev.jentikapps.helper.Cons
import net.bedev.jentikapps.helper.See
import net.bedev.jentikapps.rest.ApiConfig
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputJentik : AppCompatActivity() {

    var ValueYa = 1
    var id_kader = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_jentik)

        val sharedPreferences = getSharedPreferences("kader", Context.MODE_PRIVATE)
        id_kader = sharedPreferences.getString("id_kader", "").toString()
        See.log("id_kader : $id_kader")

        BtnInput.setOnClickListener {


//            Toast.makeText(this, "nama dusun ${dusun}, nama Rt $rt , nama Rw $rw ", Toast.LENGTH_SHORT).show()

            ProsesCeklist()

        }
    }

    private fun ProsesCeklist() {

        val nama = EdName.text.toString()
        val alamat = EdAlamat.text.toString().trim()
//        val rt = EdRt.text.toString()
//        val rw = EdRw.text.toString()

        val dusun = SpDusun.selectedItem.toString()
        val rt = SpRt.selectedItem.toString()
        val rw = SpRW.selectedItem.toString()


        if (RbYaBakMandi.isChecked) {

            TvYaJentikBakMandi.text = ValueYa.toString()
        }
        if (RbYaJentikIrigasi.isChecked) {
            TvYaJentikIrigasi.text = ValueYa.toString()

        }
        if (RbYaJentikTampung.isChecked) {
            TvYaJentikTampung.text = ValueYa.toString()

        }
        if (RbYaTempatSampah.isChecked) {
            TvYaTempatSampah.text = ValueYa.toString()

        }

        if (nama.isEmpty()) {
            EdName.error = "Harus di isi"
            return
        }

//        if (rt.isEmpty()) {
//            EdRt.error = "Harus di isi"
//            return
//        }
//        if (rw.isEmpty()) {
//            EdRw.error = "Harus di isi"
//            return
//        }

        val apiService = ApiConfig.getApiService()
        apiService.CreateJentik(
            EdName.text.toString().trim(),
            alamat,
            dusun.trim(),
            rt.trim(),
            rw.trim(),
            TvYaJentikBakMandi.text.toString().trim(),
            TvYaJentikTampung.text.toString().trim(),
            TvYaJentikIrigasi.text.toString().trim(),
            TvYaTempatSampah.text.toString().trim(),
            id_kader
        )
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    See.log("onFailure")
                    See.toast(this@InputJentik, "Cek Koneksi Intennet anda")

                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val respon = response.body()?.string()
                    See.log("respon get kader : $respon")
                    val json = JSONObject(respon)
                    val apiStatus = json.getInt(Cons.api_status)
                    if (apiStatus == 1) {
                        See.toast(this@InputJentik, "Input Data Jentik Berhasil")

                        EdName.setText("")

//                        EdRt.setText("")
//                        EdRw.setText("")
                        RbYaBakMandi.isChecked = false
                        RbNoBakMandi.isChecked = false
                        RbNoJentikIrigasi.isChecked = false
                        RbYaJentikIrigasi.isChecked = false
                        RbYaJentikTampung.isChecked = false
                        RbNoJentikTampung.isChecked = false
                        RbYaTempatSampah.isChecked = false
                        RbNoTempatSampah.isChecked = false


                    } else {
                        See.toast(this@InputJentik, "Input Data Jentik Gagal")
                    }


                }

            })
    }

}
