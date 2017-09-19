package com.omise.mobile.omisetumboon.ui.payment;

import com.omise.mobile.omisetumboon.di.scope.ActivityScoped;
import com.omise.mobile.omisetumboon.di.scope.FragmentScoped;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;


/***
 * Kotlin not generate the same bytecode as Java, Investigate..
 */

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
@Module
public abstract class PaymentActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract PaymentFragment paymentFragment();

    @ActivityScoped
    @Binds
    abstract PaymentContract.Presenter paymentPresenter(PaymentPresenter paymentPresenter);
    
    @Provides
    @Named(PaymentActivity.EXTRA_CHARITY_ID)
    @ActivityScoped
    static String provideCharityId(PaymentActivity activity) {
        return activity.getIntent().getStringExtra(PaymentActivity.EXTRA_CHARITY_ID);
    }

    @Provides
    @Named(PaymentActivity.EXTRA_CHARITY_LOGO)
    @ActivityScoped
    static String provideCharityLogoUrl(PaymentActivity activity) {
        return activity.getIntent().getStringExtra(PaymentActivity.EXTRA_CHARITY_LOGO);
    }
}


//interface PaymentActivityModule {
//
//    @FragmentScoped
//    @ContributesAndroidInjector
//    fun paymentFragment(): PaymentFragment;
//
//    @ActivityScoped
//    @Binds
//    fun paymentPresenter(): PaymentContract.Presenter
//
//    @Module
//    companion object {
//
//        @Provides
//        @ActivityScoped
//        @Named(PaymentActivity.EXTRA_CHARITY_ID)
//        fun provideCharityId(activity: PaymentActivity): String {
//            return activity.intent.getStringExtra(PaymentActivity.EXTRA_CHARITY_ID)
//        }
//
//        @Provides
//        @ActivityScoped
//        @Named(PaymentActivity.EXTRA_CHARITY_LOGO)
//        fun provideCharityLogoUrl(activity: PaymentActivity): String {
//            return activity.intent.getStringExtra(PaymentActivity.EXTRA_CHARITY_LOGO)
//        }
//    }
//}

