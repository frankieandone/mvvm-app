package one.and.frankie.mvvm.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_country.view.*
import one.and.frankie.mvvm.R
import one.and.frankie.mvvm.model.Country
import one.and.frankie.mvvm.util.getProgressDrawable
import one.and.frankie.mvvm.util.loadImage

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivCountryFlag = view.iv_country_flag
        private val tvCountryName = view.tv_country_name
        private val tvCountryCapital = view.tv_country_capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            tvCountryName.text = country.name
            tvCountryCapital.text = country.capital
            ivCountryFlag.loadImage(country.flag, progressDrawable)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))

    override fun onBindViewHolder(viewHolder: CountryViewHolder, position: Int) =
        viewHolder.bind(countries[position])

    override fun getItemCount() = countries.size

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }
}