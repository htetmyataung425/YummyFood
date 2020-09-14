package com.htetmyat.yummyfood.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color.blue
import android.net.Uri
import android.opengl.Visibility
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
import com.htetmyat.yummyfood.model.random.Meal
import com.htetmyat.yummyfood.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_countryfinal.*
import kotlinx.android.synthetic.main.fragment_randomdetails.*

class RandomDetailsFragment : Fragment()
{
    lateinit var viewModel :ViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_randomdetails, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        randomLoading()
        observeViewModel()
    }

    private fun randomLoading()
    {
        viewModel.getLoading().observe(viewLifecycleOwner, Observer { loading ->
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
        viewModel.getDetailsResult().observe(viewLifecycleOwner, Observer {
            var randomData = it.meals

            txtRandomDetailsName.text = randomData[0].strMeal
            txtRandomDetailsItemName.text = randomData[0].strCategory
            txtRandomDetailsCountry.text = randomData[0].strArea
            Glide.with(this).load(randomData[0].strMealThumb).into(imgRandomDetailsImage)
            txtRandomDetailsInstruction.text = randomData[0].strInstructions


            btnRandomDetailsIngredient.setOnClickListener {
                var builder = AlertDialog.Builder(context)
                builder.setTitle("Ingredients")
                //var ingredients : Array<String>? = arrayOf(randomData[0].strIngredient1+" = "+randomData[0].strMeasure1,randomData[0].strIngredient2+" = "+randomData[0].strMeasure2,randomData[0].strIngredient3+" = "+randomData[0].strMeasure3,randomData[0].strIngredient4+" = "+randomData[0].strMeasure4,randomData[0].strIngredient5+" = "+randomData[0].strMeasure5,randomData[0].strIngredient6+" = "+randomData[0].strMeasure6,randomData[0].strIngredient7+" = "+randomData[0].strMeasure7,randomData[0].strIngredient8+" = "+randomData[0].strMeasure8,randomData[0].strIngredient9+" = "+randomData[0].strMeasure9,randomData[0].strIngredient10+" = "+randomData[0].strMeasure10,randomData[0].strIngredient11+" = "+randomData[0].strMeasure11,randomData[0].strIngredient12+" = "+randomData[0].strMeasure12,randomData[0].strIngredient13+" = "+randomData[0].strMeasure13,randomData[0].strIngredient14+" = "+randomData[0].strMeasure14,randomData[0].strIngredient15+" = "+randomData[0].strMeasure16,randomData[0].strIngredient16.toString()+" = "+randomData[0].strMeasure16,randomData[0].strIngredient17.toString()+" = "+randomData[0].strMeasure17,randomData[0].strIngredient18.toString()+" = "+randomData[0].strMeasure18,randomData[0].strIngredient19.toString()+" = "+randomData[0].strMeasure19,randomData[0].strIngredient20.toString()+" = "+randomData[0].strMeasure20)
                val ingredients = arrayOf(randomData[0].strIngredient1,randomData[0].strIngredient2,randomData[0].strIngredient3,randomData[0].strIngredient4,randomData[0].strIngredient5,randomData[0].strIngredient6,randomData[0].strIngredient7,randomData[0].strIngredient8,randomData[0].strIngredient9,randomData[0].strIngredient10,randomData[0].strIngredient11,randomData[0].strIngredient12,randomData[0].strIngredient13,randomData[0].strIngredient4,randomData[0].strIngredient15)
                builder.setItems(ingredients) { dialog, which -> }
                val dialog = builder.create()
                dialog.show()
            }

            btnRandomDetailsRecipe.setOnClickListener {
                if (randomData[0].strSource.toString().isEmpty()) {
                    Toast.makeText(context, "This recipe link is empty.", Toast.LENGTH_LONG).show()
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW,
                        Uri.parse(randomData[0].strSource.toString())))
                }
            }

            btnRandomDetailsYoutube.setOnClickListener {
                if (randomData[0].strYoutube.isEmpty()) {
                    Toast.makeText(context, "This youtube link is empty.", Toast.LENGTH_LONG).show()
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(randomData[0].strYoutube)))
                }
            }
        })
    }

    override fun onResume()
    {
        super.onResume()
        var argument = arguments?.let { RandomDetailsFragmentArgs.fromBundle(it) }
        var accept = argument?.randomId
        viewModel.loadDetails(accept!!)

    }
}

/*
val ingredients = mapOf(
    meal.strIngredient1 to meal.strMeasure1,
    meal.strIngredient2 to meal.strMeasure2,
    meal.strIngredient3 to meal.strMeasure3,
    meal.strIngredient4 to meal.strMeasure4,
    meal.strIngredient5 to meal.strMeasure5,
    meal.strIngredient6 to meal.strMeasure6,
    meal.strIngredient7 to meal.strMeasure7,
    meal.strIngredient8 to meal.strMeasure8,
    meal.strIngredient9 to meal.strMeasure9,
    meal.strIngredient10 to meal.strMeasure10,
    meal.strIngredient11 to meal.strMeasure11,
    meal.strIngredient12 to meal.strMeasure12,
    meal.strIngredient13 to meal.strMeasure13,
    meal.strIngredient14 to meal.strMeasure14,
    meal.strIngredient15 to meal.strMeasure15,
    meal.strIngredient16 to meal.strMeasure16,
    meal.strIngredient17 to meal.strMeasure17,
    meal.strIngredient18 to meal.strMeasure18,
    meal.strIngredient19 to meal.strMeasure19,
    meal.strIngredient20 to meal.strMeasure20
)

val firstcut = ingredients.toString().replace("{", "")
val secondcut = firstcut.toString().replace("}", "")
val finalcut = secondcut.toString().replace(",", "\n")

var IngredientView =
    LayoutInflater.from(context).inflate(R.layout.dialog_ingredients_show, null)
var IngredientDialog =
    AlertDialog.Builder(context).setView(IngredientView).create()
IngredientDialog.show()
IngredientView.text_Ingredient.text = finalcut
*/

/*
var argumentArg = arguments?.let { RandomDetailsFragmentArgs.fromBundle(it) }
var text1 : String? = argumentArg?.randomname
txtrandomname.text = text1
var text2 : String? = argumentArg?.randomitem
txtrandomitemname.text = text2
var text3 : String? = argumentArg?.randomcountry
txtrandomcountry.text = argumentArg?.randomcountry
var text4 : String? = argumentArg?.randompicture
Glide.with(this).load(text4).into(imgrandomimage)
var text5 : String? = argumentArg?.randominstruction
txtrandominstruction.text = text5

btnSecondIngredient.setOnClickListener {
    //var btnIngredients : String? = argumentArg?.ingredient
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Ingredients")
    //val ingredients = arrayOf(mealData[0].strIngredient1,mealData[0].strIngredient2,mealData[0].strIngredient3,mealData[0].strIngredient4,mealData[0].strIngredient5,mealData[0].strIngredient6,mealData[0].strIngredient7,mealData[0].strIngredient8,mealData[0].strIngredient9,mealData[0].strIngredient10,mealData[0].strIngredient11,mealData[0].strIngredient12,mealData[0].strIngredient13,mealData[0].strIngredient14,mealData[0].strIngredient15)
    //,mealData[0].strIngredient16,mealData[0].strIngredient17,mealData[0].strIngredient18,mealData[0].strIngredient19,mealData[0].strIngredient20
    //builder.setItems(ingredients)  {dialog, which ->}
    val dialog = builder.create()
    dialog.show()
}
btnSecondRecipe.setOnClickListener {
    var btnrecipe : String? = argumentArg?.recipe
    startActivity ( Intent ( Intent.ACTION_VIEW, Uri.parse (btnrecipe)  )  )
}

btnSecondYoutube.setOnClickListener {
    var btnyoutube : String? = argumentArg?.youtube
    startActivity ( Intent ( Intent.ACTION_VIEW, Uri.parse (btnyoutube)  )  )
}*/
