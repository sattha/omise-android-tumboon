package com.omise.mobile.omisetumboon.ui.thankyou

import android.os.Bundle
import com.omise.mobile.omisetumboon.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class ThankYouActivity: DaggerAppCompatActivity() {

    companion object {
        const val EXTRA_DONATE_AMOUNT = "DONATE_AMOUNT"
    }

    @Inject
    lateinit var fragment: ThankYouFragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thankyou)

        supportFragmentManager.findFragmentById(R.id.contentContainer) ?:
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.contentContainer, fragment)
                        .commit()
    }
}