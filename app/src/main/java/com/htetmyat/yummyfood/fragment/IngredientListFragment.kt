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
import com.htetmyat.yummyfood.adapter.IngredientsListAdapter
import com.htetmyat.yummyfood.model.ingredientslist.Meal
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_ingredientslist.*

class IngredientListFragment : Fragment(),IngredientsListAdapter.ClickListener
{
    lateinit var viewModel: ViewModel
    lateinit var ingredientsListAdapter: IngredientsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_ingredientslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        ingredientsListAdapter = IngredientsListAdapter()
        ingredientsListRecycler.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = ingredientsListAdapter
        }

        ingredientsListLoading()
        observeViewModel()
        ingredientsListAdapter.setOnClickListener(this)
    }

    private fun ingredientsListLoading()
    {
        viewModel.getIngredientsListLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                ingredientsListProgress.visibility = View.VISIBLE
            }
            else
            {
                ingredientsListProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getIngredientsListResult().observe(viewLifecycleOwner, Observer { it
        ingredientsListAdapter.updateIngredientsList(it.meals)})
    }

    override fun onResume()
    {
        super.onResume()
        var arguments = arguments?.let { IngredientListFragmentArgs.fromBundle(it) }
        var accept = arguments?.ingredientslistId.toString()
        viewModel.loadIngredientslist(accept!!)
    }

    override fun click(meal: Meal)
    {
        var action = IngredientListFragmentDirections.actionIngredientListFragmentToIngredientsFinalFragment(meal.idMeal)
        findNavController().navigate(action)
    }
}