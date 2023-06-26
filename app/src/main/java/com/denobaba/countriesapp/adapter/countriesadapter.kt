package com.denobaba.countriesapp.adapter

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.denobaba.countriesapp.databinding.ItemRowBinding
import com.denobaba.countriesapp.model.countries
import com.denobaba.countriesapp.util.getImage
import com.denobaba.countriesapp.util.placeholderprogressbar
import com.denobaba.countriesapp.view.FeedFragementDirections

class countriesadapter(val countrieslist: ArrayList<countries>): RecyclerView.Adapter<countriesadapter.CountriesViewHolder>() {
    class CountriesViewHolder(val binding: ItemRowBinding ): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountriesViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return countrieslist.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {

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

    fun updatecountrylist(newcountrylist : List<countries>){
        countrieslist.clear()
        countrieslist.addAll(newcountrylist)
        notifyDataSetChanged()

    }

}