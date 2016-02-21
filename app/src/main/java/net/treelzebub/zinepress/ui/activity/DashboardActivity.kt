package net.treelzebub.zinepress.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.content_dashboard.*
import kotlinx.android.synthetic.main.nav_header_dashboard.*
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.db.articles.DbArticles
import net.treelzebub.zinepress.db.articles.IArticle
import net.treelzebub.zinepress.net.sync.Sync
import net.treelzebub.zinepress.ui.adapter.ArticlesAdapter
import net.treelzebub.zinepress.util.UserUtils
import net.treelzebub.zinepress.util.extensions.*
import net.treelzebub.zinepress.zine.EpubGenerator
import net.treelzebub.zinepress.zine.SelectedArticles
import rx.android.lifecycle.LifecycleObservable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_dashboard.drawer_layout as drawer
import kotlinx.android.synthetic.main.activity_dashboard.nav_view as navView

/**
 * Created by Tre Murillo on 1/2/16
 */
class DashboardActivity : AuthedRxActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val listAdapter = ArticlesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)
        reload()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setup(savedInstanceState)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> reload()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_pocket -> {
            }
            R.id.nav_library -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_settings -> {
            }
            R.id.nav_logout -> {
                UserUtils.logout(this)
                finish()
                startActivity(createIntent<MainActivity>())
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

    private fun setup(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val selectedArticles = savedInstanceState.getSerializable<Set<IArticle>>("selected_articles")
            SelectedArticles.articles.addAll(selectedArticles)
        }
        recycler.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = listAdapter
        }
        fab.setOnClickListener {
            if (SelectedArticles.articles.isEmpty()) {
                Snackbar.make(it, "No articles selected for zine!", Snackbar.LENGTH_LONG).show()
            } else {
                generateBook()
            }
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        navView.apply {
            setNavigationItemSelectedListener(this@DashboardActivity)
            onNextLayout {
                username.text = UserUtils.getName()
            }
        }
    }

    private fun reload() {
        LifecycleObservable.bindActivityLifecycle(lifecycle(),
                Sync.requestSync(this))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listAdapter.setList(DbArticles.all())
                }, {
                    Log.e(TAG, it.message)
                })
    }

    private fun handleEmpty() {
        //TODO
    }

    private fun generateBook() {
        EpubGenerator.beginBuild(SelectedArticles.articles)
    }
}
