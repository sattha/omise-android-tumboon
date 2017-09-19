package com.omise.mobile.omisetumboon

import com.facebook.stetho.Stetho
import com.omise.mobile.omisetumboon.data.RepositoryModule
import com.omise.mobile.omisetumboon.di.AppModule
import com.omise.mobile.omisetumboon.di.DaggerAppComponent
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
class App : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(RepositoryModule(BuildConfig.URL_WEB_END_POINT))
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        initRealm();
        initStetho();
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config)
    }

    private fun initStetho() {

        if (BuildConfig.DEBUG) {

            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());
        }
    }
}