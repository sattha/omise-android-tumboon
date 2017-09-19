package com.omise.mobile.omisetumboon.di

import android.app.Application
import android.content.Context
import com.omise.mobile.omisetumboon.App
import com.omise.mobile.omisetumboon.data.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by X-tivity on 9/2/2017 AD.
 */
@Module(includes = arrayOf(RepositoryModule::class))
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApp() = app

}