package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.random.Meal
import kotlinx.android.synthetic.main.item_random.view.*

class RandomAdapter (var mealList : List<Meal> = ArrayList()): RecyclerView.Adapter<RandomAdapter.RandomViewHolder>()
{
    var clickListener : ClickListener? = null
    fun setOnClickListener(clickListener : ClickListener)
    {
        this.clickListener = clickListener
    }
    fun updateRandom(mealList : List<Meal>)
    {
        this.mealList = mealList
        notifyDataSetChanged()
    }

    inner class RandomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal : Meal
        fun bindRandom(meal : Meal)
        {
            this.meal = meal
            Glide.with(itemView).load(meal.strMealThumb).into(itemView.randomimage)
            itemView.randomtext.text = meal.strMeal
        }

        override fun onClick(view : View?) {
            clickListener?.click(meal)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_random,parent,false)
        return RandomViewHolder(view)
    }
    override fun getItemCount(): Int {
        return mealList.size
    }
    override fun onBindViewHolder(holder: RandomViewHolder, position: Int) {
        holder.bindRandom(mealList[position])
    }
    interface ClickListener
    {
        fun click(meal : Meal)
    }
}