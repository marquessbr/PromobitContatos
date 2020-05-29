package com.example.android.promobitcontatos.ui.home

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armando.raonimotores.ossonhosdeaaz.controller.JSONParser
import com.example.android.promobitcontatos.R
import kotlinx.android.synthetic.main.new_contact.*

class NewContact: AppCompatActivity() {

    lateinit var btnsend: TextView
    var ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_contact)

        btnsend = btnSave
        btnsend.setOnClickListener(View.OnClickListener {
            val tudoOk = !editTextName.text.equals("") ||
                    !editTextCompany.text.equals("") ||
                    !editTextEmail.text.equals("") ||
                    !editTextPhone.text.equals("") ||
                    !editTextWebsite.text.equals("") ||
                    !editTextCustomNote.text.equals("")
            if (tudoOk) {
                ID++
                SendToSave(
                    ID.toString(),
                    editTextName.text.toString(),
                    editTextCompany!!.text.toString(),
                    editTextEmail!!.text.toString(),
                    editTextPhone!!.text.toString(),
                    editTextWebsite!!.text.toString(),
                    editTextCustomNote!!.text.toString()
                )
            }
        })
    }

    private fun SendToSave(
        id: String,
        name: String,
        company: String,
        email: String,
        phone: String,
        website: String,
        CustonNote: String) {

        SendDreamToInterpret().execute(
            id,
            name,
            company,
            email,
            phone,
            website,
            CustonNote
        )
    }

    /*
    Campos:
	name
	company
	email
	phone
	website
	custom_note
     */
    @SuppressLint("StaticFieldLeak")
    internal inner class SendDreamToInterpret : AsyncTask<String, String, String>() {
        private var did: String? = null
        private var name: String? = null
        private var company: String? = null
        private var email: String? = null
        private var phone: String? = null
        private var website: String? = null
        private var custon_note: String? = null

        override fun onPreExecute() {
            super.onPreExecute()
            btnsend!!.isEnabled = false
        }

        override fun doInBackground(vararg args: String): String? {

            did = args[0]
            name = args[1]
            company = args[2]
            email = args[3]
            phone = args[4]
            website = args[5]
            custon_note = args[6]

            //http://www.raonimotores.com.br/mobiles/savecontacts.php?ID=1&name=Armando&company=sena&email=mbr@mbr.com&phone=1199999999&website=http:www.meucu.com&custo_note=tudo%20posso%20naquele%20que%20me%20fortalece

            val params = HashMap<String, String>()
            params["ID"] = did!!
            params["name"] =  name!!
            params["company"] = company!!
            params["email"] = email!!
            params["phone"] =  phone!!
            params["website"] =  website!!
            params["custon_note"] =  custon_note!!

            return JSONParser.makeHttpRequest(urlSender, params)
        }

        override fun onPostExecute(file_url: String) {
            // dismiss the dialog once done
            val nId = Integer.valueOf(did!!)
            btnsend!!.isEnabled = true
            showMessage()
        }

    }

    private fun showMessage() {
        Toast.makeText(this, "Contato salvo com sucesso!", Toast.LENGTH_LONG).show()
    }

    companion object {
        var IsVisible = false

        private const val urlSender =
            "http://www.raonimotores.com.br/mobiles/savecontacts.php"
    }
}