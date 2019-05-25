package one.and.frankie.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import one.and.frankie.mvvm.R
import one.and.frankie.mvvm.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rc_countries_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            if (countries != null) {
                countriesAdapter.updateCountries(countries)
            }
        })
        viewModel.countryLoadError.observe(this, Observer { isError: Boolean? ->
            if (isError != null) {
                tv_list_error.visibility = if (isError) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        })
        viewModel.loading.observe(this, Observer { isLoading: Boolean? ->
            if (isLoading != null) {
                if (isLoading) {
                    pg_loading_view.visibility = View.VISIBLE
                    tv_list_error.visibility = View.GONE
                    rc_countries_list.visibility = View.GONE
                } else {
                    pg_loading_view.visibility = View.GONE
                }
            }
        })
    }
}
