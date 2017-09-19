package com.omise.mobile.omisetumboon.ui.thankyou

import javax.inject.Inject

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class ThankYouPresenter @Inject constructor(val donateAmount: String): ThankYouContract.Presenter {

    var mView: ThankYouContract.View? = null

    override fun takeView(view: ThankYouContract.View) {
        mView = view
        mView?.displayDonateAmount(donateAmount)
    }

    override fun dropView() {
        mView = null
    }

    override fun home() {
        mView?.backToHomeUi()
    }

}