package com.trootech.androidpractical.model

import com.google.gson.annotations.SerializedName

data class StoreModel(@SerializedName("franquicias")var franquicia: List<Franquicia>) {
    data class Franquicia(
        @SerializedName("APIKEY")
        var aPIKEY: String,
        @SerializedName("tokenInvu")
        var tokenInvu: String,
        @SerializedName("negocio")
        var negocio: String,
        @SerializedName("principal")
        var principal: Boolean,
        @SerializedName("id_franquicia")
        var id_franquicia: String,
        @SerializedName("franquicia")
        var franquicia: String,
        @SerializedName("horaCierreLocal")
        var horaCierreLocal: String
    )
}
