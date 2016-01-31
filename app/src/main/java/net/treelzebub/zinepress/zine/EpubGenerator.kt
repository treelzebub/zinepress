package net.treelzebub.zinepress.zine

import android.util.Log
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import nl.siegmann.epublib.domain.Book
import java.io.IOException

/**
 * Created by Tre Murillo on 1/3/16
 *
 * 1) For each selected [PocketArticle], pull the raw HTML and create an [ZineArticle]
 * 2) Compile [Zine] from list of [ZineArticle]s
 * 3) Create an ePub [Book] object
 */
object EpubGenerator {

    fun buildBook(): Book {
        val book = Book()
        val zine = createZineFromArticles()
        book.coverImage = zine.coverImage
        return book
    }

    private fun createZineFromArticles(): Zine {
        val zineArticles = arrayListOf<ZineArticle>()
        SelectedArticles.set().forEach {
            article ->
                val rawHtml = handleCallback(article.url)
                zineArticles.add(ZineArticle(article.url, article.title, rawHtml))
                Log.d("RAW HTML", rawHtml)
        }
        return Zine(null, null, null, zineArticles)
    }

    private fun handleCallback(url: String): String {
        var body: String = ""
        val callback = object : Callback {
            override fun onFailure(request: Request, e: IOException) {
                Log.e("Error handling HtmlGetter callback", e.message)
            }

            override fun onResponse(response: Response) {
                if (response.isSuccessful) {
                    body = response.body().string()
                } else {
                    //TODO
                }
            }
        }
        HtmlGetter.htmlFromUrl(url, callback)
        return body
    }
}
