package com.omise.mobile.omisetumboon.di

import com.omise.mobile.omisetumboon.di.scope.ActivityScoped
import com.omise.mobile.omisetumboon.ui.main.MainActivity
import com.omise.mobile.omisetumboon.ui.main.MainActivityModule
import com.omise.mobile.omisetumboon.ui.payment.PaymentActivity
import com.omise.mobile.omisetumboon.ui.payment.PaymentActivityModule
import com.omise.mobile.omisetumboon.ui.thankyou.ThankYouActivity
import com.omise.mobile.omisetumboon.ui.thankyou.ThankYouActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
@Module
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(PaymentActivityModule::class))
    fun paymentActivity(): PaymentActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(ThankYouActivityModule::class))
    fun thankYouActivity(): ThankYouActivity
}