package com.example.android.promobitcontatos.model

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*

object JSONParser {

    // Send Parameters method
    fun makeHttpRequest(reqURL: String, postDataParams: HashMap<String, String>?): String {

        var resultString = "fail"
        var reqParam = ""
        var mURL = URL(reqURL)

        if (postDataParams!!.size > 1) {
            reqParam = makeUrlParams(postDataParams)
            mURL = URL("$reqURL?$reqParam")
        }

        try {
            with(mURL.openConnection() as HttpURLConnection) {
                // optional default is GET
                requestMethod = "GET"

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    it.close()
                    val r = response.toString()
                    val s = r.replace("\t","")
                    val t = s.replace("}]}","}]")
                    val u = "$t,\"success\":\"1\",\"message\":\"%s\"}"
                    resultString = u
                    Log.d("ttt", "rrr")
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return resultString
    }


    private fun makeUrlParams(params: HashMap<String, String>?): String {
        val result = StringBuilder()
        var first = true
        for ((key, value) in params!!) {
            if (first)
                first = false
            else
                result.append("&")

            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value, "UTF-8"))
        }
        return result.toString()

    }
}