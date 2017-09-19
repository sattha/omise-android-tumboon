package com.omise.mobile.omisetumboon.data.remote


import com.omise.mobile.omisetumboon.data.AppDataStore
import com.omise.mobile.omisetumboon.data.BaseDataResult
import com.omise.mobile.omisetumboon.data.local.models.Charity
import com.omise.mobile.omisetumboon.data.local.models.DonateError
import com.omise.mobile.omisetumboon.data.remote.models.DonateRequest
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by X-tivity on 9/3/2017 AD.
 */
class AppRemoteDataStore @Inject constructor(retrofit: Retrofit) : AppDataStore {

    private val mApiService = retrofit.create(ApiService::class.java);

    override fun donate(name: String, omiseToken: String, amount: String): Observable<BaseDataResult<DonateError>> {
        return mApiService
                .donate(DonateRequest(name, omiseToken, amount))
                .flatMap { (objType, loc, code, message) ->
                    Observable.fromCallable({
                        BaseDataResult(
                                true,
                                "success",
                                DonateError(objType, loc, code, message))
                    })
                }
    }

    override fun getCharityList(isForceReload: Boolean): Observable<BaseDataResult<List<Charity>>> {
        return mApiService
                .charityList()
                .flatMap {
                    Observable.fromCallable({
                        BaseDataResult(
                                true,
                                "success",
                                it)
                    })
                }
    }

    override fun setCharity(charity: List<Charity>): Observable<BaseDataResult<List<Charity>>> {
        TODO("implemented by local data store")
    }
}