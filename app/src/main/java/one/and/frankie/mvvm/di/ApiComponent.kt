package one.and.frankie.mvvm.di

import dagger.Component
import one.and.frankie.mvvm.model.CountriesService

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)
}