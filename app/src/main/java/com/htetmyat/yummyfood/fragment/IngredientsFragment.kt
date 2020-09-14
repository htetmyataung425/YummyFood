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
import com.htetmyat.yummyfood.adapter.IngredientsAdapter
import com.htetmyat.yummyfood.model.Ingredients.Meal
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_ingredients.*


class IngredientsFragment : Fragment(),IngredientsAdapter.ClickListener
{
    lateinit var viewModel: ViewModel
    lateinit var ingredientsAdapter: IngredientsAdapter

    override fun onCreateView(inflater:LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        ingredientsAdapter = IngredientsAdapter()
        ingredientsRecycler.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = ingredientsAdapter
        }

        ingredientsLoading()
        observeViewModel()
        ingredientsAdapter.setOnClickListener(this)
    }

    private fun ingredientsLoading()
    {
        viewModel.getIngredientsLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                ingredientsProgress.visibility = View.VISIBLE
            }
            else
            {
                ingredientsProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getIngredientsResult().observe(viewLifecycleOwner, Observer { it
        ingredientsAdapter.updateIngredient(it.meals)})
    }

    override fun onResume()
    {
        super.onResume()
        var arguments = arguments?.let { IngredientsFragmentArgs.fromBundle(it) }
        var accept = arguments?.ingredient.toString()
        viewModel.loadIngredients(accept!!)
    }

    override fun click(meal: Meal) {
        var action = IngredientsFragmentDirections.actionIngredientsFragmentToIngredientListFragment(meal.strIngredient)
        findNavController().navigate(action)
    }

}