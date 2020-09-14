package com.htetmyat.yummyfood.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_letterfinal.*

class LetterFinalFragment : Fragment()
{
    lateinit var viewModel : ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_letterfinal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

       viewModel = ViewModelProvider(this).get(ViewModel :: class.java)

        letterLoading()
        observeViewModel()
    }

    private fun letterLoading()
    {
        viewModel.getLoading().observe(viewLifecycleOwner, Observer { loading ->
            if(loading)
            {
                letterProgressbar.visibility = View.VISIBLE
            }
            else
            {
                letterProgressbar.visibility = View.INVISIBLE
            }
        })
    }

    private fun observeViewModel()
    {
        viewModel.getDetailsResult().observe(viewLifecycleOwner, Observer {
            var letterData = it.meals

            txtLetterFinalName.text = letterData[0].strMeal
            txtLetterFinalItemName.text = letterData[0].strCategory
            txtLetterFinalCountry.text = letterData[0].strArea
            Glide.with(this).load(letterData[0].strMealThumb).into(imgLetterFinalImage)
            txtLetterFinalInstruction.text = letterData[0].strInstructions

            btnLetterFinalIngredient.setOnClickListener {
                var builder = AlertDialog.Builder(context)
                builder.setTitle("Ingredients")
                val ingredients = arrayOf(letterData[0].strIngredient1,letterData[0].strIngredient2,letterData[0].strIngredient3,letterData[0].strIngredient4,letterData[0].strIngredient5,letterData[0].strIngredient6,letterData[0].strIngredient7,letterData[0].strIngredient8,letterData[0].strIngredient9,letterData[0].strIngredient10,letterData[0].strIngredient11,letterData[0].strIngredient12,letterData[0].strIngredient13,letterData[0].strIngredient4,letterData[0].strIngredient15)
                builder.setItems(ingredients){dialog,which ->}
                val dialog = builder.create()
                dialog.show()
            }

            //btnLetterFinalRecipe.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(letterData[0].strSource.toString()))) }
            btnLetterFinalRecipe.setOnClickListener{
                if(letterData[0].strSource.toString().isEmpty())
                {
                    Toast.makeText(context,"This recipe link is empty.",Toast.LENGTH_LONG).show()
                }
                else
                {
                    startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(letterData[0].strSource.toString())))
                }
            }


            //btnLetterFinalYoutube.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(letterData[0].strYoutube))) }
            btnLetterFinalYoutube.setOnClickListener{
                if(letterData[0].strYoutube.isEmpty())
                {
                    Toast.makeText(context,"This youtube link is empty.",Toast.LENGTH_LONG).show()
                }
                else
                {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(letterData[0].strYoutube)))
                }
            }
        })
    }
    override fun onResume()
    {
        super.onResume()
        var argument = arguments?.let { LetterFinalFragmentArgs.fromBundle(it) }
        var accept = argument?.chooseId
        viewModel.loadDetails(accept!!)
    }
}
