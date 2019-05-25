package one.and.frankie.mvvm.model

import io.reactivex.Single
import one.and.frankie.mvvm.di.DaggerApiComponent
import javax.inject.Inject

class CountriesService {
    @Inject lateinit var api: CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<List<Country>> = api.getCountries()
}