package com.omise.mobile.omisetumboon.ui.payment

import android.os.Bundle
import com.omise.mobile.omisetumboon.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class PaymentActivity: DaggerAppCompatActivity() {

    companion object {
        const val EXTRA_CHARITY_ID = "CHARITY_ID"
        const val EXTRA_CHARITY_LOGO = "CHARITY_LOGO"
    }

    @Inject
    lateinit var fragment: PaymentFragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        setSupportActionBar(toolbar)
        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)
        ab.setDisplayShowTitleEnabled(false)

        supportFragmentManager.findFragmentById(R.id.contentContainer) ?:
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.contentContainer, fragment)
                        .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}