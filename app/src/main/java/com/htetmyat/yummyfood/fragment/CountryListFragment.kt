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
import com.htetmyat.yummyfood.adapter.CountryListAdapter
import com.htetmyat.yummyfood.model.countrylist.Meal
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_countrylist.*


class CountryListFragment : Fragment(),CountryListAdapter.ClickListener
{
    lateinit var viewModel : ViewModel
    lateinit var countryListAdapter: CountryListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_countrylist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        countryListAdapter = CountryListAdapter()
        countryListRecycler.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = countryListAdapter
        }
        countryListLoading()
        observeViewModel()
        countryListAdapter.setOnClickListener(this)
    }

    private fun countryListLoading()
    {
        viewModel.getCountryListLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                countryListProgress.visibility = View.VISIBLE
            }
            else
            {
                countryListProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getCountryListResult().observe(viewLifecycleOwner, Observer { it
        countryListAdapter.upDateCountryList(it.meals)})
    }

    override fun onResume()
    {
        super.onResume()
        var argument = arguments?.let { CountryFragmentArgs.fromBundle(it) }
        var accept = argument?.countryid
        viewModel.loadCountryList(accept!!)
    }

    override fun click(meal: Meal) {
        var action = CountryListFragmentDirections.actionCountryListFragmentToCountryFinalFragment(meal.idMeal)
        findNavController().navigate(action)
    }
}