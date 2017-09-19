package com.omise.mobile.omisetumboon.ui.thankyou

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omise.mobile.omisetumboon.R
import com.omise.mobile.omisetumboon.di.scope.ActivityScoped
import com.omise.mobile.omisetumboon.ui.main.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_thankyou.*
import javax.inject.Inject

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
@ActivityScoped
class ThankYouFragment @Inject constructor(): DaggerFragment(), ThankYouContract.View {

    @Inject
    lateinit var presenter: ThankYouContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_thankyou, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnHome.setOnClickListener({ presenter.home() })
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    override fun displayDonateAmount(amount: String) {
        tvDonateAmount.text = "THB ${amount}"
    }

    override fun backToHomeUi() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}