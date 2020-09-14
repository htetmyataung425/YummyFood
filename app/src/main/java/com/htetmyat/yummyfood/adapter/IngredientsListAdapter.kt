package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.ingredientslist.Meal
import kotlinx.android.synthetic.main.item_ingredientslist.view.*

class IngredientsListAdapter(var ingredientsListList : List<Meal> = ArrayList()):RecyclerView.Adapter<IngredientsListAdapter.IngredientsListViewHolder>()
{
    var clickListener:ClickListener?=null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener=clickListener
    }
    fun updateIngredientsList(ingredientsListList : List<Meal>)
    {
       this.ingredientsListList=ingredientsListList
        notifyDataSetChanged()
    }
    inner class IngredientsListViewHolder(itemView : View):RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal: Meal
        fun bindIngredientsList(meal:Meal)
        {
            this.meal=meal
            Glide.with(itemView).load(meal.strMealThumb).into(itemView.imgIngredientsListImage)
            itemView.txtIngredientsListName.text = meal.strMeal
        }
        override fun onClick(p0: View?)
        {
            clickListener?.click(meal)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsListViewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredientslist,parent,false)
        return IngredientsListViewHolder(view)
    }
    override fun getItemCount(): Int
    {
        return ingredientsListList.size
    }
    override fun onBindViewHolder(holder: IngredientsListViewHolder, position: Int)
    {
        holder.bindIngredientsList(ingredientsListList[position])
    }
    interface ClickListener
    {
        fun click(meal: Meal)
    }
}