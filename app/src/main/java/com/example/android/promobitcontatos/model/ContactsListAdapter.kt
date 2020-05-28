package com.example.android.promobitcontatos.model

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.android.promobitcontatos.R
import java.util.ArrayList

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

        val newContactText = itemLayout!!.findViewById<View>(R.id.NewContactText) as TextView
        newContactText.isClickable = false

        val btnMore = itemLayout!!.findViewById<View>(R.id.BtnMore) as ImageView

        return itemLayout as LinearLayout
    }
}
