package net.bedev.jentikapps.model

data class get_kader(
    val api_status: String,
    val `data`: List<Data>,
    val message: String
) {
    data class Data(
        val id_kader: String,
        val nama_kader: String,
        val password: String,
        val tanggal: String,
        val username: String
    )
}