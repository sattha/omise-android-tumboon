package com.omise.mobile.omisetumboon.data

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.omise.mobile.omisetumboon.data.local.AppLocalDataStore
import com.omise.mobile.omisetumboon.data.remote.AppRemoteDataStore
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by X-tivity on 9/18/2017 AD.
 */
@Module
class RepositoryModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun providedSharedPreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    fun provideOkHttpCache(app: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(app.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(logging)
                .build();
    }

    @Provides
    @Singleton
    fun providedGson(): Gson {
        return GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesAppLocalDataStore(app: Application): AppLocalDataStore {
        return AppLocalDataStore(app)
    }

    @Provides
    @Singleton
    fun providesAppRemoteDataStore(retrofit: Retrofit): AppRemoteDataStore {
        return AppRemoteDataStore(retrofit)
    }

    @Provides
    @Singleton
    fun providesRepository(remoteData: AppRemoteDataStore,
                           localData: AppLocalDataStore): AppRepository {

        return AppRepository(remoteData, localData);
    }
}