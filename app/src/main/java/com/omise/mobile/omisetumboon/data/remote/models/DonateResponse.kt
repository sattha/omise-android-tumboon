package com.omise.mobile.omisetumboon.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.omise.mobile.omisetumboon.data.local.models.DonateError

/**
 * Created by X-tivity on 9/5/2017 AD.
 */
data class DonateResponse(

        @Expose
        @SerializedName("object")
        var objectType: String = "",

        @Expose
        @SerializedName("location")
        var location: String = "",

        @Expose
        @SerializedName("code")
        var code: String = "",

        @Expose
        @SerializedName("message")
        var message: String = ""

)