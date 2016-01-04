package net.treelzebub.zinepress

import java.util.ArrayList

/**
 * Created by Tre Murillo on 1/3/16
 */
object ZineArticles {

    // List of urls to collect as ePub
    private val urls: ArrayList<String> = arrayListOf()

    fun add(vararg url: String) {
        urls.addAll(url)
    }

    fun remove(vararg url: String) {
        urls.removeAll(url)
    }

    fun list(): List<String> {
        return urls
    }
}
