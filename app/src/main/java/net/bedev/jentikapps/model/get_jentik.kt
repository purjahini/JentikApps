package net.bedev.jentikapps.model

data class get_jentik(
    val api_status: String,
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val alamat: String,
        val bak_mandi: Int,
        val dusun : String,
        val id_periksajentik: String,
        val nama_lengkap: String,
        val penampungan_air: Int,
        val rt: String,
        val rw: String,
        val saluran_irigasi: Int,
        val tanggal: String,
        val tempat_sampah : Int
    )
}