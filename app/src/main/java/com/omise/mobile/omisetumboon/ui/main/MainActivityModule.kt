package com.omise.mobile.omisetumboon.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.omise.mobile.omisetumboon.di.scope.FragmentScoped
import dagger.Binds
import com.omise.mobile.omisetumboon.di.scope.ActivityScoped





/**
 * Created by X-tivity on 9/18/2017 AD.
 */
@Module
interface MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    fun mainFragment(): MainFragment

    @ActivityScoped
    @Binds
    fun mainPresenter(presenter: MainPresenter): MainContract.Presenter
}