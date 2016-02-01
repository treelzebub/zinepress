package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.app_bar_dashboard.*
import kotlinx.android.synthetic.main.content_dashboard.*
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.db.articles.DbArticles
import net.treelzebub.zinepress.db.articles.IArticle
import net.treelzebub.zinepress.ui.adapter.ArticlesAdapter
import net.treelzebub.zinepress.zine.EpubGenerator
import net.treelzebub.zinepress.zine.SelectedArticles
import rx.android.schedulers.AndroidSchedulers
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.activity_dashboard.drawer_layout as drawer
import kotlinx.android.synthetic.main.activity_dashboard.nav_view as navView

/**
 * Created by Tre Murillo on 1/2/16
 */
class DashboardActivity : BaseRxActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var tokenMgr: PocketTokenManager by Delegates.notNull()

    private val adapter = ArticlesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        tokenMgr = PocketTokenManager.from(this)
        setSupportActionBar(toolbar)
        setup()
        reload()
        if (savedInstanceState != null) {
            val selectedArticles = savedInstanceState.getSerializable("selected_articles")
            if (selectedArticles != null) {
                SelectedArticles.articles.addAll(selectedArticles as Set<IArticle>)
            }
        }
    }

    override fun onPause() {
        warnDataLossOrDo { super.onPause() }
    }

    override fun onDestroy() {
        warnDataLossOrDo { super.onDestroy() }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            warnDataLossOrDo { super.onBackPressed() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_manage -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_send -> {
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (SelectedArticles.articles.isNotEmpty()) {
            outState.putSerializable("selected_articles", SelectedArticles.articles)
        }
    }

    private fun setup() {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        fab.setOnClickListener {
            if (SelectedArticles.articles.isEmpty()) {
                Snackbar.make(it, "No articles selected for zine!", Snackbar.LENGTH_LONG).show()
            } else {
                EpubGenerator.buildBook()
            }
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun reload() {
        // Check if logged in, refresh articles
        tokenMgr.storage.hasAccessToken()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    loggedIn ->
                    if (loggedIn) {
                        loadArticles()
                    } else {
                        showLogin()
                    }
                }
    }

    private fun loadArticles() {
        //TODO try out SQLBrite
        adapter.setList(DbArticles.get(this).all())
//        bindActivityLifecycle(lifecycle(), articlesObservable)
//                .observeOn(mainThread())
//                .subscribe {
//                    val articles = it.articles
//                    if (articles.isEmpty()) {
//                        handleEmpty()
//                    } else {
////                        adapter.setList(articles)
//                    }
//                }
    }

    private fun showLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun handleEmpty() {
        //TODO
    }

    private fun warnDataLossOrDo(fn: () -> Unit) {
        if (SelectedArticles.articles.isNotEmpty()) {
            warnDataLoss()
        } else {
            fn()
        }
    }

    private fun warnDataLoss() {
        AlertDialog.Builder(this)
                .setTitle(R.string.alert_data_loss_title)
                .setMessage(R.string.alert_data_loss_message)
                .setPositiveButton(R.string.yes, {
                    dialog, which ->
                    dialog.dismiss()
                })
                .setNegativeButton(R.string.no, {
                    dialog, which ->
                    onBackPressed()
                })
    }
}
