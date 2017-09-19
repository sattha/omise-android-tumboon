package com.omise.mobile.omisetumboon.ui.main

import com.google.gson.Gson
import com.omise.mobile.omisetumboon.data.AppRepository
import com.omise.mobile.omisetumboon.data.BaseDataResult
import com.omise.mobile.omisetumboon.data.CallbackWrapper
import com.omise.mobile.omisetumboon.data.local.models.Charity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class MainPresenter @Inject constructor(val appRepository: AppRepository, val gson: Gson): MainContract.Presenter {

    private var mView: MainContract.View? = null;
    private var mFirstLoad = true

    override fun takeView(view: MainContract.View) {
        mView = view
        loadCharity(false)
    }

    override fun dropView() {
        mView = null
    }

    override fun loadCharity(isForceReload: Boolean) {
        // Simplification for sample: a network reload will be forced on first load.
        mView?.showLoadingIndicator()
        appRepository.getCharityList(isForceReload || mFirstLoad)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackWrapper<BaseDataResult<List<Charity>>>(gson) {

                    override fun onEmitNext(t: BaseDataResult<List<Charity>>) {
                        mView?.showListViewByHideEmptyView()
                        mView?.updateListItem(t.data)
                    }

                    override fun onEmitError(s: String) {

                        mView?.dismissLoadingIndicator()

                        if (mView?.charityItemCount ?: 0 > 0) {
                            mView?.showErrorDialog(s)
                        } else {
                            mView?.showEmptyViewByHideListView(s)
                        }
                    }

                    override fun onComplete() {

                        mView?.dismissLoadingIndicator()

                        if (mView?.charityItemCount ?: 0 == 0) {
                            mView?.showEmptyViewByHideListView("No Data Found")
                        }
                    }
                })

        mFirstLoad = false;
    }

    override fun openPayment(charity: Charity) {
        mView?.showPaymentUi(charity)
    }

}