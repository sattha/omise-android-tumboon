package com.omise.mobile.omisetumboon.data

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.omise.mobile.omisetumboon.data.local.models.DonateError
import com.omise.mobile.omisetumboon.ui.BaseView
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.lang.ref.WeakReference
import java.net.SocketTimeoutException


/**
 * Created by X-tivity on 9/19/2017 AD.
 */
abstract class CallbackWrapper<T> : DisposableObserver<T> {

    //BaseView is just a reference of a View in MVP
    private val gson: Gson

    constructor(gson: Gson) : super() {
        this.gson = gson
    }

    abstract fun onEmitNext(t: T)

    abstract fun onEmitError(s: String)

    override fun onComplete() {

    }

    override fun onNext(t: T) {
        //You can return StatusCodes of different cases from your API and handle it here.
        // I usually include these cases on BaseResponse and inherit it from every Response
        onEmitNext(t)
    }

    override fun onError(e: Throwable) {
        var errMessage = ""
        when (e) {
            is HttpException -> {
                val responseBody = e.response().errorBody()
                errMessage = getErrorMessage(responseBody)
            }
            is SocketTimeoutException -> errMessage = "Connection Timeout"
            else -> errMessage = e.message ?: ""
        }

        onEmitError(errMessage)
    }

    private fun getErrorMessage(responseBody: ResponseBody?): String {

        var errModel = DonateError()

        return try {
            errModel = gson.fromJson(responseBody?.string(), DonateError::class.java)
            errModel.message
        } catch (err: JsonSyntaxException) {
            errModel.message
        }
    }
}