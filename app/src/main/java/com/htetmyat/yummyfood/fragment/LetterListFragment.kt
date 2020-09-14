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
import com.htetmyat.yummyfood.adapter.LetterListAdapter
import com.htetmyat.yummyfood.model.letterList.Meal
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_letterlist.*
import kotlinx.android.synthetic.main.item_letterlist.*

class LetterListFragment : Fragment(),LetterListAdapter.ClickListener
{
    lateinit var viewModel : ViewModel
    lateinit var letterListAdapter : LetterListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_letterlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModel :: class.java)

        letterListAdapter = LetterListAdapter()
        letterListRecycler.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter= letterListAdapter
        }
        letterLoading()
        observeViewModel()
        letterListAdapter.setOnClickListener(this)
    }

    private fun letterLoading()
    {
        viewModel.getLetterListLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                letterListProgress.visibility = View.VISIBLE
            }
            else
            {
                letterListProgress.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getLetterListResult().observe(viewLifecycleOwner, Observer {it
            letterListAdapter.updateLetterList(it.meals)
        })
    }

    override fun onResume()
    {
        super.onResume()
        var argument = arguments?.let { LetterListFragmentArgs.fromBundle(it) }
        var accept = argument?.letter
        viewModel.loadLetterList(accept!!)
    }

    override fun click(meal: Meal)
    {
        var action = LetterListFragmentDirections.actionLetterListFragmentToLetterFinalFragment(meal.idMeal)
        findNavController().navigate(action)
    }
}