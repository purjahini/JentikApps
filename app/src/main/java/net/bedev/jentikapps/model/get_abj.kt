package net.bedev.jentikapps.model


import com.google.gson.annotations.SerializedName

data class get_abj(
    @SerializedName("api_status")
    var apiStatus: String,
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: String
) {
    data class Data(
        @SerializedName("JmlJentikRmh")
        var jmlJentikRmh: String,
        @SerializedName("JmlRumah")
        var jmlRumah: String
    )
}