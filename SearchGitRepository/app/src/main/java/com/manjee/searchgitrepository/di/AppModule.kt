package com.manjee.searchgitrepository.di

import com.manjee.searchgitrepository.api.SearchApi
import com.manjee.searchgitrepository.api.UrlProvider
import com.manjee.searchgitrepository.data.repository.SearchRepository
import com.manjee.searchgitrepository.data.repository.SearchRepositoryImpl
import com.manjee.searchgitrepository.view.RepositorySearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUrlProvider() = UrlProvider()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
    }

    @Provides
    @Singleton
    fun provideV1Retrofit(
        okHttpClient: OkHttpClient,
        urlProvider: UrlProvider
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(urlProvider.getGitApiUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providerSearchApiService(retrofit: Retrofit) = retrofit.create(SearchApi::class.java)!!

    @Singleton
    @Provides
    fun providerSearchRepository(searchApi: SearchApi): SearchRepository {
        return SearchRepositoryImpl(searchApi)
    }

    @Provides
    fun providerRepositorySearchAdapter(): RepositorySearchAdapter {
        return RepositorySearchAdapter()
    }
}