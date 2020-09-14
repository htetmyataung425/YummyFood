package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.search.Meal
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(var searchList : List<Meal> = ArrayList()):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()
{
    var clickListener:ClickListener?=null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener=clickListener
    }
    fun updateSearch(searchList : List<Meal>)
    {
        this.searchList=searchList
        notifyDataSetChanged()
    }
    inner class SearchViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init
        {
            itemView.setOnClickListener(this)
        }
        lateinit var meal: Meal
        fun bindSearch(meal:Meal)
        {
            this.meal=meal
            Glide.with(itemView).load(meal.strMealThumb).into(itemView.imgSearchImage)
            itemView.txtSearchName.text = meal.strMeal
        }
        override fun onClick(view: View?)
        {
             clickListener?.click(meal)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_search,parent,false)
        return SearchViewHolder(view)
    }
    override fun getItemCount(): Int
    {
        return searchList.size
    }
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int)
    {
        holder.bindSearch(searchList.get(position))
    }
    interface ClickListener
    {
        fun click(meal: Meal)
    }
}