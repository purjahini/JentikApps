package net.bedev.jentikapps.model


import com.google.gson.annotations.SerializedName

data class get_jentiks(
    @SerializedName("api_status")
    var apiStatus: String,
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String
) {
    data class Data(
        @SerializedName("alamat")
        var alamat: String,
        @SerializedName("bak_mandi")
        var bakMandi: String,
        @SerializedName("dispenser")
        var dispenser: String,
        @SerializedName("dusun")
        var dusun: String,
        @SerializedName("id_kader")
        var idKader: String,
        @SerializedName("id_periksajentik")
        var idPeriksajentik: String,
        @SerializedName("kulkas")
        var kulkas: String,
        @SerializedName("nama_lengkap")
        var namaLengkap: String,
        @SerializedName("pecahan_botol")
        var pecahanBotol: String,
        @SerializedName("penampungan_air")
        var penampunganAir: String,
        @SerializedName("pot_bunga")
        var potBunga: String,
        @SerializedName("rt")
        var rt: String,
        @SerializedName("rw")
        var rw: String,
        @SerializedName("saluran_irigasi")
        var saluranIrigasi: String,
        @SerializedName("tandon_air")
        var tandonAir: String,
        @SerializedName("tanggal")
        var tanggal: String,
        @SerializedName("tempat_sampah")
        var tempatSampah: String,
        @SerializedName("tempayan")
        var tempayan: String,
        @SerializedName("vas_bunga")
        var vasBunga: String
    )
}