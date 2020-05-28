package com.example.android.promobitcontatos.model

class ContactItem(
    id: Int,
    name: String,
    company: String,
    photo : String,
    new: Boolean
) {

    private var mId: Int = id
    private var mName: String = name
    private var mCompany: String = company
    private var mPhoto : String = photo
    private var mNew: Boolean = new

    fun getId(): Int {
        return this.mId
    }

    fun getName(): String {
        return this.mName
    }

    fun getCompany(): String {
        return this.mCompany
    }

    fun getPhoto(): String {
        return this.mPhoto
    }

    fun getNew(): Boolean {
        return this.mNew
    }
}