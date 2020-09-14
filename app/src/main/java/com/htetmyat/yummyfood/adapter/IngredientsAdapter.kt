package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.Ingredients.Meal
import kotlinx.android.synthetic.main.item_ingredients.view.*

class IngredientsAdapter(var ingredientsList : List<Meal> = ArrayList()):RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>()
{
    var clickListener:ClickListener?=null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener = clickListener
    }
    fun updateIngredient(ingredientsList : List<Meal>)
    {
        this.ingredientsList=ingredientsList
        notifyDataSetChanged()
    }
    inner class IngredientsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal: Meal
        fun bindIngredients(meal: Meal)
        {
            this.meal = meal
            itemView.txtIngredients.text = meal.strIngredient
        }

        override fun onClick(view : View?)
        {
            clickListener?.click(meal)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
         var view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients,parent,false)
        return IngredientsViewHolder(view)
    }
    override fun getItemCount(): Int
    {
        return ingredientsList.size
    }
    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int)
    {
        holder.bindIngredients(ingredientsList[position])
    }
    interface ClickListener
    {
        fun click(meal: Meal)
    }
}