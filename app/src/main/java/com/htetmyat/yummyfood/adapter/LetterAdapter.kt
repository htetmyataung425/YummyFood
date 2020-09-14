package com.htetmyat.yummyfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.model.letter.Letter
import kotlinx.android.synthetic.main.item_letter.view.*

class LetterAdapter (var letterList:ArrayList<Letter>):RecyclerView.Adapter<LetterAdapter.LetterViewHolder>()
{
    var clickListener : ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener)
    {
        this.clickListener = clickListener
    }

    inner class LetterViewHolder(itemView : View): RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        lateinit var letter: Letter
        fun bindLetter(letter: Letter)
        {
            this.letter = letter
            itemView.txtLetter.text = letter.letter     }

        override fun onClick(p0: View?) {
             clickListener?.click(letter)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
     var view = LayoutInflater.from(parent.context).inflate(R.layout.item_letter,parent,false)
        return LetterViewHolder(view)
    }
    override fun getItemCount(): Int
    {
        return letterList.size
    }
    override fun onBindViewHolder(holder: LetterViewHolder, position: Int)
    {
        holder.bindLetter(letterList[position])
    }
    interface ClickListener
    {
        fun click(letter: Letter)
    }
}