package com.omise.mobile.omisetumboon.ui.payment

import co.omise.android.CardNumber
import co.omise.android.Client
import co.omise.android.TokenRequest
import co.omise.android.TokenRequestListener
import co.omise.android.models.Token
import com.google.gson.Gson
import com.omise.mobile.omisetumboon.data.AppRepository
import com.omise.mobile.omisetumboon.data.BaseDataResult
import com.omise.mobile.omisetumboon.data.CallbackWrapper
import com.omise.mobile.omisetumboon.data.local.models.DonateError
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named


/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class PaymentPresenter @Inject constructor(
        val appRepository: AppRepository,
        val gson: Gson,
        @Named(PaymentActivity.EXTRA_CHARITY_ID) var charityId: String,
        @Named(PaymentActivity.EXTRA_CHARITY_LOGO) var charityLogoUrl: String
) : PaymentContract.Presenter {

    var mView: PaymentContract.View? = null;

    override fun takeView(view: PaymentContract.View) {
        mView = view;
    }

    override fun dropView() {
        mView = null
    }

    override fun donate() {

        if (!isValidatePaymentInfo()) {
            return
        }

        // block ui, start payment process..
        mView?.showLoadingIndicator()

        // request payment token from Omise
        createToken(object : TokenRequestListener {

            override fun onTokenRequestSucceed(request: TokenRequest?, token: Token?) {

                // request payment with backend
                appRepository.donate(
                        mView?.creditCardName() ?: "",
                        token?.id ?: "",
                        normalizeDonateAmount(mView?.donateAmount()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : CallbackWrapper<BaseDataResult<DonateError>>(gson) {

                            override fun onEmitNext(t: BaseDataResult<DonateError>) {
                                // finish unblock ui..
                                mView?.dismissLoadingIndicator()
                                mView?.showThankYouUi(mView?.donateAmount() ?: "")
                            }

                            override fun onEmitError(s: String) {
                                // failure unblock ui..
                                mView?.dismissLoadingIndicator()
                                mView?.showErrorDialog(s)
                            }
                        })
            }

            override fun onTokenRequestFailed(request: TokenRequest?, throws: Throwable?) {
                mView?.dismissLoadingIndicator()
                mView?.showErrorDialog(throws?.message ?: "There is something wrong.")
            }
        })
    }

    private fun normalizeDonateAmount(number: String?): String {
        return if (number == null) "" else {
            number
                    .replace(",", "0")
                    .replace("[^0-9]".toRegex(), "")
        }
    }

    private fun createToken(listener: TokenRequestListener) {

        val client = Client("pkey_test_59aoo3fwrjls2ue3rf4")

        val request = TokenRequest()
        request.number = CardNumber.normalize(mView?.creditCardNumber())
        request.name = mView?.creditCardName()
        request.expirationMonth = mView?.expirationMonth() ?: 0
        request.expirationYear = mView?.expirationYear() ?: 0
        request.securityCode = mView?.securityCode()

        client.send(request, listener)
    }


    private fun isValidatePaymentInfo(): Boolean {

        if (!CardNumber.luhn(mView?.creditCardNumber())) {
            mView?.showErrorDialog("Invalid credit card number")
            return false;
        }

        if (mView?.creditCardName().isNullOrBlank()) {
            mView?.showErrorDialog("Invalid credit card name")
            return false;
        }

        if (mView?.securityCode().isNullOrBlank() ||
                !mView?.securityCode()!!.matches(Regex("^[0-9]{3,3}$"))) {

            mView?.showErrorDialog("Invalid security code")
            return false;
        }


        if (mView?.donateAmount().isNullOrBlank() ||
                !normalizeDonateAmount(mView?.donateAmount()).matches(
                        Regex("^[0-9]{0,}(\\.?)[0-9]{0,}$"))) {

            mView?.showErrorDialog("Invalid donate amount")
            return false;
        }

        return true;
    }
}