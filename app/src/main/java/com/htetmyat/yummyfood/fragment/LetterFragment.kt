package com.htetmyat.yummyfood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.htetmyat.yummyfood.R
import com.htetmyat.yummyfood.adapter.LetterAdapter
import com.htetmyat.yummyfood.model.letter.Letter
import kotlinx.android.synthetic.main.fragment_letter.*

class LetterFragment : Fragment(),LetterAdapter.ClickListener
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_letter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        var letterdata = ArrayList<Letter>()
        letterdata.add(Letter("A"))
        letterdata.add(Letter("B"))
        letterdata.add(Letter("C"))
        letterdata.add(Letter("D"))
        letterdata.add(Letter("E"))
        letterdata.add(Letter("F"))
        letterdata.add(Letter("G"))
        letterdata.add(Letter("H"))
        letterdata.add(Letter("I"))
        letterdata.add(Letter("J"))
        letterdata.add(Letter("K"))
        letterdata.add(Letter("L"))
        letterdata.add(Letter("M"))
        letterdata.add(Letter("N"))
        letterdata.add(Letter("O"))
        letterdata.add(Letter("P"))
        letterdata.add(Letter("Q"))
        letterdata.add(Letter("R"))
        letterdata.add(Letter("S"))
        letterdata.add(Letter("T"))
        letterdata.add(Letter("U"))
        letterdata.add(Letter("V"))
        letterdata.add(Letter("W"))
        letterdata.add(Letter("X"))
        letterdata.add(Letter("Y"))
        letterdata.add(Letter("Z"))

        var fakeLetterAdapter = LetterAdapter(letterdata)
        letterRecycler.apply {
            layoutManager = GridLayoutManager(context,4)
            adapter = fakeLetterAdapter
        }
        fakeLetterAdapter.setOnClickListener(this)
    }
    override fun click(letter: Letter)
    {
        var action = LetterFragmentDirections.actionLetterFragmentToLetterListFragment(letter.letter)
        findNavController().navigate(action)
    }
}