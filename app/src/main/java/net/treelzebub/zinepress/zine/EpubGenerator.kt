package net.treelzebub.zinepress.zine

import android.util.Log
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import net.treelzebub.zinepress.db.articles.IArticle
import net.treelzebub.zinepress.db.zines.DbZines
import net.treelzebub.zinepress.db.zines.IZine
import net.treelzebub.zinepress.util.BaseInjection
import net.treelzebub.zinepress.util.ToastUtils
import net.treelzebub.zinepress.util.extensions.TAG
import nl.siegmann.epublib.domain.Book
import org.joda.time.DateTime
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.IOException
import java.util.*

/**
 * Created by Tre Murillo on 1/3/16
 *
 * 1) For each selected [PocketArticle], pull the raw HTML and create an [ZineArticle]
 * 2) Compile [Zine] from list of [ZineArticle]s
 * 3) Create an ePub [Book] object
 */
object EpubGenerator {

    private val htmlMap = hashMapOf<IArticle, String>()

    fun buildAndObserve(articles: Set<IArticle>) {
        htmlFromArticles(articles)
        Observable.just(htmlMap).repeat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.size == SelectedArticles.articles.size) {
                        toZineArticles(htmlMap)
                    }
                }
    }

    private fun toZineArticles(htmlMap: Map<IArticle, String>) {
        val zineArticles = arrayListOf<ZineArticle>()
        htmlMap.forEach {
            it.key.apply {
                val za = ZineArticle(id, date, pocketUrl(), title, it.value)
                zineArticles.add(za)
            }
        }
        createZine(zineArticles)
    }

    private fun createZine(zineArticles: List<ZineArticle>) {
        val zine = Zine(
                zineArticles.first().id,
                DateTime.now().millis,
                "My First Zine", //TODO we'll have to prompt the user for title and other customizations
                zineArticles.toHashSet())
        writeZine(zine) // Zine is written to db to allow later editing.
    }

    private fun writeZine(zine: IZine) {
        DbZines.write().addOrUpdate(zine)
    }

    private fun htmlFromArticles(articles: Set<IArticle>) {
        articles.forEach {
            listHtml(it)
        }
    }

    private fun listHtml(article: IArticle) {
        handleCallback(article.pocketUrl(), HtmlCallback(article))
    }

    private fun handleCallback(url: String, callback: Callback) {
        HtmlGetter.htmlFromUrl(url, callback)
    }

    class HtmlCallback(val article: IArticle) : Callback {
        override fun onFailure(request: Request, e: IOException) {
            Log.e(TAG, e.message)
        }

        override fun onResponse(response: Response) {
            if (response.isSuccessful) {
                htmlMap.put(article, response.body().string())
                Log.d(TAG, "Got HTML from Article ${article.id}.")
            } else {
                Log.e(TAG, response.message())
            }
        }
    }
}
