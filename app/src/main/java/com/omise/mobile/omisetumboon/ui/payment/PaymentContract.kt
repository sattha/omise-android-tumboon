package com.omise.mobile.omisetumboon.ui.payment

import com.omise.mobile.omisetumboon.ui.BasePresenter
import com.omise.mobile.omisetumboon.ui.BaseView

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
interface PaymentContract {

    interface View : BaseView<Presenter> {

        fun creditCardNumber(): String

        fun creditCardName(): String

        fun securityCode(): String

        fun expirationMonth(): Int

        fun expirationYear(): Int

        fun donateAmount(): String;

        fun showLoadingIndicator()

        fun dismissLoadingIndicator()

        fun showErrorDialog(message: String)

        fun showThankYouUi(amount: String)
    }

    interface Presenter: BasePresenter<View> {

        fun donate()
    }
}