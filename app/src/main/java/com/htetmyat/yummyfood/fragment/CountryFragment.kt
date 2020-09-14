package com.htetmyat.yummyfood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.adapter.CountryAdapter
//import com.example.yummyfood.fragment.LetterFragmentArgs.Companion.fromBundle
import com.htetmyat.yummyfood.model.country.Meal
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment(),CountryAdapter.ClickListener
{
    lateinit var viewModel : ViewModel
    lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        countryAdapter = CountryAdapter()
        countryRecycler.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = countryAdapter
        }

        countryLoading()
        observeViewModel()
        countryAdapter.setOnClickListener(this)
    }

    private fun countryLoading()
    {
        viewModel.getCountryLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                countryProgress.visibility = View.VISIBLE
            }
            else
            {
                countryProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getCountryResult().observe(viewLifecycleOwner, Observer { it ->
            countryAdapter.updateCountry(it.meals)
        })
    }

    override fun onResume()
    {
        super.onResume()
        var argument = arguments?.let { CountryFragmentArgs.fromBundle(it) }
        var a = argument?.countryid
        viewModel.loadCountry(a!!)
    }

    override fun click(meal: Meal) {
        var action = CountryFragmentDirections.actionCountryFragmentToCountryListFragment(meal.strArea)
        findNavController().navigate(action)
    }


}