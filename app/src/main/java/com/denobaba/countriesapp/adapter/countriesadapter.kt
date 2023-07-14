package com.denobaba.countriesapp.adapter

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.denobaba.countriesapp.R
import com.denobaba.countriesapp.databinding.ItemRowBinding
import com.denobaba.countriesapp.model.Countries
import com.denobaba.countriesapp.util.getImage
import com.denobaba.countriesapp.util.placeholderprogressbar
import com.denobaba.countriesapp.view.FeedFragementDirections

class countriesadapter(val countrieslist: ArrayList<Countries>): RecyclerView.Adapter<countriesadapter.CountriesViewHolder>(),CountryClickListener {
    class CountriesViewHolder(val binding: ItemRowBinding ): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {



        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountriesViewHolder(binding)


        /*
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemRowBinding>(inflater, R.layout.item_row,parent,false)
        return CountriesViewHolder(view)

         */


    }

    override fun getItemCount(): Int {
        return countrieslist.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.binding.country = countrieslist[position]
        holder.binding.listener = this

    }
    /*
        holder.binding.name.text = countrieslist[position].CountryName
        holder.binding.capital.text = countrieslist[position].CountryCapital
        holder.binding.imagehere.getImage(countrieslist[position].CountryFlag,
            placeholderprogressbar(holder.itemView.context)
        )

        holder.itemView.setOnClickListener {
            val action= FeedFragementDirections.actionFeedFragementToCountriesFragment(countrieslist[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }
    }

     */

    fun updatecountrylist(newcountrylist : List<Countries>){
        countrieslist.clear()
        countrieslist.addAll(newcountrylist)
        notifyDataSetChanged()

    }

    override fun onCountryClicked(v: View) {
        val binding = DataBindingUtil.getBinding<ItemRowBinding>(v)
        val uuid = binding?.country?.uuid ?: 0

        val action = FeedFragementDirections.actionFeedFragementToCountriesFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}