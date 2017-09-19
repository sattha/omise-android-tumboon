package com.omise.mobile.omisetumboon.data.local.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by X-tivity on 9/3/2017 AD.
 *
 * Your model has to extend RealmObject. Furthermore, the class must be annotated with open
 * (Kotlin classes are final by default).
 */

open class Charity(

        // You can put properties in the constructor as long as all of them are initialized with
        // default values. This ensures that an empty constructor is generated.
        // All properties are by default persisted.
        // Properties can be annotated with PrimaryKey or Index.
        // If you use non-nullable types, properties must be initialized with non-null values.
        @PrimaryKey
        @Expose
        @SerializedName("id")
        var id: Long = 0L,

        @Expose
        @SerializedName("name")
        var name: String = "",

        @Expose
        @SerializedName("logo_url")
        var logoUrl: String = ""

) : RealmObject()