package com.naldana.ejemplo10.utilities

import android.net.Uri
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class NetworkUtils {
    val COINS_API_BASE_URL = "192.168.1.10:3000/api/"
    val POKEMON_INFO = "coins"
    val POKEMON_TYPE = "type"

    private val TAG = NetworkUtils::class.java.simpleName

    fun buildUrl(root: String, coinID: String): URL {
        val builtUri = Uri.parse(COINS_API_BASE_URL)
            .buildUpon()
            .appendPath(root)
            .appendPath(coinID)
            .build()

        val url = try {
            URL(builtUri.toString())
        } catch (e: MalformedURLException) {
            URL("")
        }

        Log.d(TAG, "Built URI $url")

        return url
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String {
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput) {
                scanner.next()
            } else {
                ""
            }
        } finally {
            urlConnection.disconnect()
        }
    }
}