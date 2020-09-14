package com.htetmyat.yummyfood.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.adapter.CategoryAdapter
import com.htetmyat.yummyfood.adapter.RandomAdapter
import com.htetmyat.yummyfood.model.category.CategoryX
import com.htetmyat.yummyfood.model.random.Meal
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment(),RandomAdapter.ClickListener,CategoryAdapter.ClickListener
{
    lateinit var viewModel : ViewModel
    lateinit var randomAdapter: RandomAdapter
    //Category
    lateinit var categoryAdapter: CategoryAdapter

    private var searchView: androidx.appcompat.widget.SearchView? = null
    private var queryTextListener: androidx.appcompat.widget.SearchView.OnQueryTextListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        randomAdapter = RandomAdapter()
        randomrecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = randomAdapter
        }
        randomAdapter.setOnClickListener(this)
        observeViewModel()

        //Category
        categoryAdapter = CategoryAdapter()
        categoryrecycler.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = categoryAdapter
        }
        randomLoading()
        observeViewModel()
        categoryAdapter.setOnClickListener(this)
        //Category

        //Letter
        letterCardView.setOnClickListener {
            var action = FirstFragmentDirections.actionFirstFragmentToLetterFragment()
            findNavController().navigate(action)
        }
        //CountryList
        countryCardView.setOnClickListener{
            var action = FirstFragmentDirections.actionFirstFragmentToCountryFragment()
            findNavController().navigate(action)
        }
        //Ingredients
        ingredientCardView.setOnClickListener{
            var action = FirstFragmentDirections.actionFirstFragmentToIngredientsFragment()
            findNavController().navigate(action)
        }
    }

    private fun randomLoading()
    {
        viewModel.getRandomLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                randomProgress.visibility = View.VISIBLE
            }
            else
            {
                randomProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getRandomResult().observe(viewLifecycleOwner, Observer { news ->
            randomAdapter.updateRandom(news.meals)
        })
        //Category
        viewModel.getCategoryResult().observe(viewLifecycleOwner, Observer { news ->
            categoryAdapter.updateCategory(news.categories)
        })
    }
    override fun onResume()
    {
        super.onResume()
        viewModel.loadRandom()
        //Category
        viewModel.loadCategory()
    }

    override fun click(meal: Meal)
    {
        var action = FirstFragmentDirections.actionFirstFragmentToRandomdetailsFragment(meal.idMeal)
        findNavController().navigate(action)
    }
    override fun click(categoryx: CategoryX)
    {
        var action1 = FirstFragmentDirections.actionFirstFragmentToCategorylistFragment(categoryx.strCategory)
        findNavController().navigate(action1)
    }
}