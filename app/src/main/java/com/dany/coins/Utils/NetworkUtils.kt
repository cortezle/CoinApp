package com.dany.coins.Utils

import android.net.Uri
import android.util.Log

import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.Scanner

object NetworkUtils {
    //Cambiar a API coins
    private const val COIN_API_BASE_URL = "https://thawing-stream-49083.herokuapp.com/"
    private const val COIN_INFO = "coins"

    private val TAG = NetworkUtils::class.java.simpleName

    fun buildUrl(pleca:String): URL? {
        val builtUri = Uri.parse(COIN_API_BASE_URL)
                .buildUpon()
                .appendPath(pleca)
                .build()

        var url: URL? = null
        try {
            url = URL(builtUri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        Log.d(TAG, "Built URI " + url!!)

        return url
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String? {
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput) {
                scanner.next()
            } else {
                null
            }
        } finally {
            urlConnection.disconnect()
        }
    }
}