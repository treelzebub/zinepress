package net.treelzebub.zinepress.zine

import com.squareup.okhttp.Call
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request

/**
 * Created by Tre Murillo on 1/3/16
 */
object HtmlGetter {

    fun handleCallback(url: String, callback: Callback): Call {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }
}
