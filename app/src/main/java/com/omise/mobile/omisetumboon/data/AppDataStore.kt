package com.omise.mobile.omisetumboon.data

import com.omise.mobile.omisetumboon.data.local.models.Charity
import com.omise.mobile.omisetumboon.data.local.models.DonateError
import io.reactivex.Observable

/**
 * Created by X-tivity on 9/3/2017 AD.
 */
interface AppDataStore {

    fun donate(name: String, omiseToken: String, amount: String): Observable<BaseDataResult<DonateError>>

    fun getCharityList(isForceReload: Boolean = false): Observable<BaseDataResult<List<Charity>>>

    fun setCharity(charity: List<Charity>): Observable<BaseDataResult<List<Charity>>>
}