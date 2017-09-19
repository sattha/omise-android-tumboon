package com.omise.mobile.omisetumboon.data

import com.omise.mobile.omisetumboon.data.local.AppLocalDataStore
import com.omise.mobile.omisetumboon.data.local.models.Charity
import com.omise.mobile.omisetumboon.data.local.models.DonateError
import com.omise.mobile.omisetumboon.data.remote.AppRemoteDataStore
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by X-tivity on 9/3/2017 AD.
 */
class AppRepository : AppDataStore {

    private val mRemoteData: AppRemoteDataStore;
    private val mLocalData: AppLocalDataStore;

    @Inject
    constructor(remoteData: AppRemoteDataStore, localData: AppLocalDataStore) {
        mRemoteData = remoteData;
        mLocalData = localData;
    }

    override fun donate(name: String, omiseToken: String, amount: String): Observable<BaseDataResult<DonateError>> {
        return mRemoteData
                .donate(name, omiseToken, amount)
    }

    override fun getCharityList(isForceReload: Boolean): Observable<BaseDataResult<List<Charity>>> {

        val localObs = mLocalData
                .getCharityList(isForceReload)

        val remoteObs = mRemoteData
                .getCharityList(isForceReload)
                .concatMap({
                    // save profile to local data store
                    mLocalData.setCharity(it.data)
                })

        if (isForceReload) {
            return Observable
                    .concat(localObs, remoteObs)
                    .onErrorResumeNext(localObs)
        } else {
            return localObs
                    .concatMap({
                        if (it.data.isEmpty()) {
                            remoteObs
                        } else {
                            Observable.just(it)
                        }
                    })
        }
    }

    override fun setCharity(charity: List<Charity>): Observable<BaseDataResult<List<Charity>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}