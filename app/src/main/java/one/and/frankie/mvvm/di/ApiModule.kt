package one.and.frankie.mvvm.di

import dagger.Module
import dagger.Provides
import one.and.frankie.mvvm.model.CountriesApi
import one.and.frankie.mvvm.model.CountriesService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com"
    }

    @Provides fun provideCountriesApi(): CountriesApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::
            class.java)

    @Provides fun providesCountriesService(): CountriesService =
            CountriesService()
}