package net.bedev.jentikapps


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import net.bedev.jentikapps.helper.Cons
import net.bedev.jentikapps.helper.See
import net.bedev.jentikapps.model.get_kader
import net.bedev.jentikapps.rest.ApiConfig
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        Btnlogin.setOnClickListener {
            postLogin()
            PbLogin.visibility = View.VISIBLE
        }
    }

    private fun postLogin() {

        val username = EdUsername.text.toString()
        val pass = EdPassword.text.toString()
        if (username.isEmpty() || pass.isEmpty()){
            EdUsername.error = "Harus diisi"
            EdPassword.error = "Harus di isi"
            return
        }

        val apiService = ApiConfig.getApiService()
        apiService.GetKader(EdUsername.text.toString().trim(), EdPassword.text.toString().trim())
            .enqueue(object :Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    See.log("onFailure")
                    See.toast(this@Login, "Cek Koneksi Intennet anda")
                    PbLogin.visibility = View.GONE
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val  respon = response.body()?.string()
                    See.log("respon get kader : $respon")
                    val json = JSONObject(respon)
                    val apiStatus = json.getInt(Cons.api_status)

                    if (apiStatus == 1){
                        val data = Gson().fromJson(respon, get_kader::class.java).data
                        data.map {
                            val id_kader = it.id_kader
                            val nama_kader = it.nama_kader
                            val username = it.username

                            See.log("data kader : $id_kader, $nama_kader, $username")

                            val sharedPreferences = getSharedPreferences("kader", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("id_kader", id_kader)
                            editor.putString("nama_kader", nama_kader)
                            editor.putString("username", username)
                            editor.apply()

                            val intent = Intent(this@Login , InputJentik::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    } else
                    {
                        See.toast(this@Login, "Silahkan masukkan email dan password dengan benar")

                    }
                }

            })

    }
}
