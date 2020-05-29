package com.example.android.promobitcontatos.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.android.promobitcontatos.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import java.util.*

class ContactsListAdapter(
    private val mContext: Context
) : BaseAdapter() {

    private val mItems = ArrayList<ContactItem>()
    private var itemLayout: LinearLayout? = null

    fun add(item: ContactItem) {
        mItems.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        mItems.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mItems.size
    }

    override fun getItem(pos: Int): Any {
        return mItems[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contactItem = getItem(position) as ContactItem

        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root: ViewGroup? = null
        itemLayout = inflater.inflate(R.layout.contact_item, root) as LinearLayout

        val nameContactText = itemLayout!!.findViewById<View>(R.id.NameContactText) as TextView
        nameContactText.text = contactItem.getName()

        val companyContactText = itemLayout!!.findViewById<View>(R.id.CompanyContactText) as TextView
        companyContactText.text = contactItem.getCompany()

        val contactPhoto = itemLayout!!.findViewById<View>(R.id.ContactPhoto) as ImageView
        Picasso.get().load(contactItem.getPhoto()).into(contactPhoto);

        val newContactText = itemLayout!!.findViewById<View>(R.id.NewContactText) as TextView
        newContactText.visibility = View.INVISIBLE
        if (contactItem.getNew())
            newContactText.visibility = View.VISIBLE

        val btnMore = itemLayout!!.findViewById<View>(R.id.BtnMore) as ImageView
        btnMore.setOnClickListener(View.OnClickListener { TODO("") })

        return itemLayout as LinearLayout
    }

}
