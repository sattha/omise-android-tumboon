package com.omise.mobile.omisetumboon.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by X-tivity on 9/5/2017 AD.
 */
data class DonateRequest(

        @Expose
        @SerializedName("name")
        val name: String,

        @Expose
        @SerializedName("token")
        val token: String,

        @Expose
        @SerializedName("amount")
        val amount: String

)