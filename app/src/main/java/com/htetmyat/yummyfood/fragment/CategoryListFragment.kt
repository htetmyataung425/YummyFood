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
import com.htetmyat.yummyfood.adapter.CategoryListAdapter
import com.htetmyat.yummyfood.model.categorylist.Meal
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_categorylist.*

class CategoryListFragment : Fragment(),CategoryListAdapter.ClickListener
{
     lateinit var viewModel : ViewModel
     lateinit var categoryListAdapter: CategoryListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_categorylist, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel :: class.java)

        categoryListAdapter = CategoryListAdapter()
        categoryListRecycler.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = categoryListAdapter
        }

        categoryListLoading()
        observeViewModel()
        categoryListAdapter.setOnClickListener(this)
    }

    private fun categoryListLoading()
    {
        viewModel.getCategoryListLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                categoryListProgress.visibility = View.VISIBLE
            }
            else
            {
                categoryListProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getCategoryListResult().observe(viewLifecycleOwner, Observer { it ->
            categoryListAdapter.updateCategoryList(it.meals)
            //Log.d("helo","${it.meals}")
        })
    }
    override fun onResume()
    {
        super.onResume()
        var arguments = arguments?.let { CategoryListFragmentArgs.fromBundle(it) }
        var accept = arguments?.category
        viewModel.loadCategoryList(accept!!)
    }

    override fun click(meal: Meal)
    {
        var action = CategoryListFragmentDirections.actionCategorylistFragmentToCategoryfinalFragment(meal.idMeal)
        findNavController().navigate(action)
    }

}
