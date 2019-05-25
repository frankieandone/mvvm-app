package one.and.frankie.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import one.and.frankie.mvvm.di.DaggerApiComponent
import one.and.frankie.mvvm.model.CountriesService
import one.and.frankie.mvvm.model.Country
import javax.inject.Inject

class ListViewModel : ViewModel() {
    @Inject lateinit var countriesService: CountriesService
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(countriesService.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(value: List<Country>) {
                    countries.value = value
                    countryLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    countryLoadError.value = true
                    loading.value = false
                }
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}