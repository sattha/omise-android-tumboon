package com.omise.mobile.omisetumboon.data.remote

import com.omise.mobile.omisetumboon.data.local.models.Charity
import com.omise.mobile.omisetumboon.data.remote.models.DonateRequest
import com.omise.mobile.omisetumboon.data.remote.models.DonateResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by X-tivity on 9/4/2017 AD.
 */
interface ApiService {

    @GET("/")
    fun charityList(): Observable<List<Charity>>

    @POST("donate")
    fun donate(@Body request: DonateRequest): Observable<DonateResponse>

}