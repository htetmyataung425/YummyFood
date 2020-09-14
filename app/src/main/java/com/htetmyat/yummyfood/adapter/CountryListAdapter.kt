package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.countrylist.Meal
import kotlinx.android.synthetic.main.item_countrylist.view.*

class CountryListAdapter(var countryListList : List<Meal> = ArrayList()):RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>()
{
    private var clickListener : ClickListener?= null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener=clickListener
    }
    fun upDateCountryList(countryListList : List<Meal>)
    {
        this.countryListList = countryListList
        notifyDataSetChanged()
    }
    inner class CountryListViewHolder(itemView : View):RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal :Meal
        fun bindCountryList(meal : Meal)
        {
            this.meal= meal
            Glide.with(itemView).load(meal.strMealThumb).into(itemView.imgCountryListImage)
            itemView.txtCountryListName.text = meal.strMeal
        }

        override fun onClick(p0: View?)
        {
            clickListener?.click(meal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_countrylist,parent,false)
        return CountryListViewHolder(view)
    }
    override fun getItemCount(): Int
    {
       return countryListList.size
    }
    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int)
    {
        holder.bindCountryList(countryListList[position])
    }
    interface ClickListener
    {
        fun click(meal: Meal)
    }
}