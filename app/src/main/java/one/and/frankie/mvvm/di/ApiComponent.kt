package one.and.frankie.mvvm.di

import dagger.Component
import one.and.frankie.mvvm.model.CountriesService
import one.and.frankie.mvvm.viewmodel.ListViewModel

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)
}