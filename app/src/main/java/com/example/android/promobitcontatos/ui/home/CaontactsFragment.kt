package com.example.android.promobitcontatos.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.promobitcontatos.R
import com.example.android.promobitcontatos.model.ContactItem
import com.example.android.promobitcontatos.model.ContactsListAdapter
import java.util.ArrayList

class CaontactsFragment : Fragment() {

    lateinit var mProgressBar: ProgressBar
    private var mContactItem: ListView? = null
    private var contacts: ArrayList<ContactItem>? = null

    var mAdapter: ContactsListAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contacts, container, false)



        return root
    }
}