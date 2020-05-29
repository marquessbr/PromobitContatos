package com.example.android.promobitcontatos.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.armando.raonimotores.ossonhosdeaaz.controller.JSONParser
import com.example.android.promobitcontatos.R
import com.example.android.promobitcontatos.model.ContactItem
import com.example.android.promobitcontatos.model.ContactsListAdapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashMap

class CaontactsFragment : Fragment() {

    enum class Filter {TUDO,COM_FOTO, NOME }
    private lateinit var mContext: Context

    lateinit var mProgressBar: ProgressBar
    private var mContactsItens: ListView? = null
    private var mContactsArray: ArrayList<ContactItem> = ArrayList<ContactItem>()

    var mAdapter: ContactsListAdapter? = null

    private val urlSender =
        "http://www.raonimotores.com.br/mobiles/getcontacts.php"
    private var contactsList: ArrayList<HashMap<String, String>>? = null
    private var jsonResult: JSONArray? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contacts, container, false)

        mProgressBar = root.findViewById(R.id.Pbar)

        mContactsItens = root.findViewById<View>(R.id.lv_contacts) as ListView
        mContactsItens!!.adapter = null

        mAdapter = ContactsListAdapter(mContext)
        contactsList = ArrayList()
        if (isNetworkConnected())
            LoadContacts().execute("")
        else {
            TODO("implementar o placeholder para no ineternet")
        }
        mContactsItens!!.adapter = mAdapter
        return root
    }

    @Suppress("DEPRECATION")
    fun isNetworkConnected(): Boolean {
        var result = false
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    @SuppressLint("StaticFieldLeak")
    inner class LoadContacts : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            // Show Progressbar for batter UI
            mProgressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg voids: String?): String {

            val email = voids[0]
            val params = HashMap<String, String>()
            params["email"] = email as String
            val json = JSONParser.makeHttpRequest(urlSender, params)
            return if (json == "") {
                "0"
            } else json
        }

        override fun onPostExecute(results: String?) {
            // Hide Progressbar
            mProgressBar.visibility = View.GONE

            if (results != null) {
                // See Response in Logcat for understand JSON Results and make DeveloperList
                Log.i("onPostExecute: ", results)
            }


            try {
                val json = JSONObject(results)
                val isSucess = json.getString(Companion.TAG_SUCCESS)
                if (isSucess != "0") {
                    jsonResult = json.getJSONArray(Companion.TAG_MASTER)
                    for (i in 0 until jsonResult!!.length()) {
                        val c = jsonResult!!.getJSONObject(i)
                        val nID: Int = c.getInt(Companion.TAG_ID)
                        val cname = c.getString(TAG_NAME)
                        val ccompany = c.getString(TAG_COMPANY)
                        val cphoto = c.getString(TAG_PHOTO)
                        val cnew = c.getString(TAG_NEW)
                        val map = HashMap<String, String>()
                        map[Companion.TAG_ID] = nID.toString()
                        map[TAG_NAME] = cname
                        map[TAG_COMPANY] = ccompany
                        map[TAG_PHOTO] = cphoto
                        map[TAG_NEW] = cnew

                        // adding HashList to ArrayList
                        contactsList!!.add(map)
                    }

                    if (contactsList!!.size > 0) {
                        var oitem: ContactItem
                        for (it in contactsList!!) {
                            oitem = ContactItem(
                                it[TAG_ID]!!.toInt(),
                                it[TAG_NAME]!!,
                                it[TAG_COMPANY]!!,
                                it[TAG_PHOTO]!!,
                                if (it[TAG_NEW]!! == "true") true else false)
                            mContactsArray!!.add(oitem)
                        }
                        fillAdapter(Filter.COM_FOTO)
                    }
                } else {
                    Toast.makeText(mContext, "Nenhum contato", Toast.LENGTH_SHORT).show()
                    mAdapter!!.clear()
                }

            } catch (e: JSONException) {
                Toast.makeText(mContext, "Erro na busca", Toast.LENGTH_SHORT).show()
                mAdapter!!.clear()
                e.printStackTrace()
            }

        }
    }

    private fun fillAdapter(filter: Filter) {
        mAdapter!!.clear()
        when (filter) {
            Filter.TUDO -> {
                for (item in mContactsArray!!)
                    mAdapter!!.add(item)
            }
            Filter.COM_FOTO -> {
                for (item in mContactsArray!!)
                    if (item.getPhoto() != "https://i.pravatar.cc/300")
                        mAdapter!!.add(item)
            }
            Filter.NOME -> {}
        }
    }

    companion object {
        private const val TAG_MASTER = "data"
        private const val TAG_SUCCESS = "success"
        private const val TAG_ID = "id"
        private const val TAG_NAME = "name"
        private const val TAG_COMPANY = "company"
        private const val TAG_PHOTO = "photo"
        private const val TAG_NEW = "new"

        /*
            id: Int,
    name: String,
    company: String,
    photo : String,
    new: Boolean


"id": 2,
			"name": "Fabio Ribeiro",
			"company": "Asus",
			"photo" : "https://i.pravatar.cc/300",
			"new": true
        */
    }
}