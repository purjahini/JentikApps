package net.bedev.jentikapps.rest


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("get_kader.php")
    fun GetKader(
        @Field("username") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("getabj.php")
    fun GetAbj(
        @Field("rt") rt:String,
        @Field("rw") rw:String

    ) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("create_jentik.php")
    fun CreateJentik(
        @Field("nama_lengkap") nama_lengkap: String,
        @Field("alamat") alamat: String,
        @Field("dusun") dusun: String,
        @Field("rt") rt: String,
        @Field("rw") rw: String,
        @Field("bak_mandi") bak_mandi: String,
        @Field("penampungan_air") penampungan_air: String,
        @Field("saluran_irigasi") saluran_irigasi: String,
        @Field("tempat_sampah") tempat_sampah: String,
        @Field("pecahan_botol") pecahan_botol: String,
        @Field("tempayan") tempayan: String,
        @Field("kulkas") kulkas: String,
        @Field("dispenser") dispenser: String,
        @Field("tandon_air") tandon_air: String,
        @Field("vas_bunga") vas_bunga: String,
        @Field("pot_bunga") pot_bunga: String,
        @Field("id_kader") id_kader: String

    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("delete_jentik.php")
    fun DeleteJentik(
        @Field("id_periksajentik") id_periksajentik: String
    ): Call<ResponseBody>


    @GET("get_jentik.php")
    fun getJentik(): Call<ResponseBody>
}