package com.denobaba.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.denobaba.countriesapp.R
import com.denobaba.countriesapp.adapter.countriesadapter
import com.denobaba.countriesapp.databinding.FragmentFirstFragementBinding
import com.denobaba.countriesapp.model.countries
import com.denobaba.countriesapp.viewmodel.FeedViewModel


class FeedFragement : Fragment() {
    private var _binding: FragmentFirstFragementBinding? = null
    private val binding get() = _binding!!




    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = countriesadapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first_fragement, container, false)
        _binding = FragmentFirstFragementBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshdata()
        binding.recylecountry.layoutManager = LinearLayoutManager(context) //itemler alt alta sıralanacak o yüzden
        binding.recylecountry.adapter = countryAdapter
        observelivedata()

        binding.swiperefreshlayout.setOnRefreshListener {
            binding.recylecountry.visibility = View.GONE
            binding.error.visibility = View.GONE
            binding.countryloading.visibility = View.VISIBLE
            viewModel.refreshdata()
            binding.swiperefreshlayout.isRefreshing= false
        }

    }

    fun observelivedata(){
        viewModel.countries1.observe(viewLifecycleOwner, Observer {countries ->
            countries?.let {
                binding.recylecountry.visibility = View.VISIBLE
                countryAdapter.updatecountrylist(countries)
            }

        })

        viewModel.error.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if(it){
                    binding.error.visibility = View.VISIBLE

                }else{
                    binding.error.visibility = View.GONE
                }
            }

        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {loading ->
            loading?.let {
                if(it){
                    binding.countryloading.visibility= View.VISIBLE
                    binding.error.visibility = View.GONE
                    binding.error.visibility = View.GONE

                }else{
                    binding.countryloading.visibility = View.GONE
                }



            }


        })
    }

}