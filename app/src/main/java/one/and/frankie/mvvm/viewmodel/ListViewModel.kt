package one.and.frankie.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import one.and.frankie.mvvm.model.Country

class ListViewModel: ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf(
            Country("Afghanistan"),
            Country("Albania"),
            Country("Algeria"),
            Country("Andorra"),
            Country("Angola"),
            Country("Antigua and Barbuda"),
            Country("Argentina")
        )

        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }
}