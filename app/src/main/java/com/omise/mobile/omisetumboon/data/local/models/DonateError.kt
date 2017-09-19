package com.omise.mobile.omisetumboon.data.local.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by X-tivity on 9/10/2017 AD.
 */
data class DonateError(
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