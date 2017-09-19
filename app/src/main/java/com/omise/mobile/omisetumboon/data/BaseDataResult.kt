package com.omise.mobile.omisetumboon.data

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
data class BaseDataResult<T>(

        val isSuccess: Boolean,
        val message: String,
        val data: T

)