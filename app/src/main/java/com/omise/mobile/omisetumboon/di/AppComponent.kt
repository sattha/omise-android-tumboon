package com.omise.mobile.omisetumboon.di

import android.app.Application
import com.omise.mobile.omisetumboon.App
import com.omise.mobile.omisetumboon.data.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by X-tivity on 9/2/2017 AD.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class)
)

interface AppComponent : AndroidInjector<App> {

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun appModule(module: AppModule): Builder

        fun dataModule(module: RepositoryModule): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}