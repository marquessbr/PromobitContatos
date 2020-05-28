package com.example.android.promobitcontatos.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.promobitcontatos.R

class CardsFragment : Fragment() {

    private lateinit var cardsViewModel: CardsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        cardsViewModel =
                ViewModelProvider(this).get(CardsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cards, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        cardsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}