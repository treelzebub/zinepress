package net.treelzebub.zinepress.async

import android.os.AsyncTask

/**
 * Created by Tre Murillo on 1/2/16
 */
fun async(fn: () -> Unit) {
    object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            fn()
            return null
        }
    }.execute(null)
}

fun <T> async(fn: () -> T, post: (result: T) -> Unit) {
    object : AsyncTask<Void, Void, T>() {
        override fun doInBackground(vararg params: Void): T {
            return fn()
        }

        override fun onPostExecute(result: T) {
            post(result)
        }
    }.execute(null)
}
