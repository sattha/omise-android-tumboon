package com.omise.mobile.omisetumboon.ui.thankyou

import com.omise.mobile.omisetumboon.ui.BasePresenter
import com.omise.mobile.omisetumboon.ui.BaseView

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class ThankYouContract {

    interface View : BaseView<Presenter> {

        fun displayDonateAmount(amount: String)

        fun backToHomeUi()
    }

    interface Presenter : BasePresenter<View> {

        fun home()
    }
}