package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.letterList.Meal
import kotlinx.android.synthetic.main.item_letterlist.view.*

class LetterListAdapter(var letterList : List<Meal> = ArrayList()): RecyclerView.Adapter<LetterListAdapter.LetterListViewHolder>()
{
    var clickListener : ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener = clickListener
    }
    fun updateLetterList(letterList : List<Meal>)
    {
        this.letterList = letterList
        notifyDataSetChanged()
    }
    inner class LetterListViewHolder(itemView : View): RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var meal: Meal
        fun bindLetterList(meal: Meal)
        {
            this.meal=meal
            Glide.with(itemView).load(meal.strMealThumb).into(itemView.imgLetterListImage)
            itemView.txtLetterListName.text = meal.strMeal
        }

        override fun onClick(p0: View?)
        {
            clickListener?.click(meal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_letterlist,parent,false)
        return LetterListViewHolder(view)
    }

    override fun getItemCount(): Int
    {
       return letterList.size
    }
    override fun onBindViewHolder(holder: LetterListViewHolder, position: Int)
    {
        holder.bindLetterList(letterList[position])
    }
    interface ClickListener
    {
        fun click(meal : Meal)
    }

}