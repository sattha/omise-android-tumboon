package com.omise.mobile.omisetumboon.data.local

import android.content.Context
import com.omise.mobile.omisetumboon.data.AppDataStore
import com.omise.mobile.omisetumboon.data.BaseDataResult
import com.omise.mobile.omisetumboon.data.local.models.Charity
import com.omise.mobile.omisetumboon.data.local.models.DonateError
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.saveAll
import io.reactivex.Observable

/**
 * Created by X-tivity on 9/3/2017 AD.
 */
class AppLocalDataStore(context: Context) : AppDataStore {

    override fun donate(name: String, omiseToken: String, amount: String): Observable<BaseDataResult<DonateError>> {
        TODO("implemented by remote data store")
    }

    override fun getCharityList(isForceReload: Boolean): Observable<BaseDataResult<List<Charity>>> {

        return Observable.fromCallable({
            val charities = Charity().queryAll();
            BaseDataResult(true, "success", charities)
        })
    }

    override fun setCharity(charity: List<Charity>):  Observable<BaseDataResult<List<Charity>>> {

        return Observable.fromCallable({
            charity.saveAll();
            BaseDataResult(true, "success", charity)
        })
    }
}