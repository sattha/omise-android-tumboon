package com.omise.mobile.omisetumboon.ui.thankyou;

import com.omise.mobile.omisetumboon.di.scope.ActivityScoped;
import com.omise.mobile.omisetumboon.di.scope.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
@Module
public abstract class ThankYouActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ThankYouFragment thankYouFragment();

    @ActivityScoped
    @Binds
    abstract ThankYouContract.Presenter thankYouPresenter(ThankYouPresenter presenter);

    @Provides
    @ActivityScoped
    static String provideExtraDonateAmount(ThankYouActivity activity) {
        return activity.getIntent().getStringExtra(ThankYouActivity.EXTRA_DONATE_AMOUNT);
    }
}
