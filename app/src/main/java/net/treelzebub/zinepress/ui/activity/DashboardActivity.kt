package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import rx.android.schedulers.AndroidSchedulers
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.R
import net.treelzebub.zinepress.api.PocketApiFactory
import net.treelzebub.zinepress.auth.PocketTokenManager
import net.treelzebub.zinepress.zine.ZineArticles
import net.treelzebub.zinepress.auth.model.AuthedRequestBody
import net.treelzebub.zinepress.ui.adapter.ArticlesAdapter

import kotlinx.android.synthetic.main.app_bar_dashboard.*
import kotlinx.android.synthetic.main.content_dashboard.*
import kotlinx.android.synthetic.main.activity_dashboard.drawer_layout as drawer
import kotlinx.android.synthetic.main.activity_dashboard.nav_view as navView


/**
 * Created by Tre Murillo on 1/2/16
 */
class DashboardActivity : BaseRxActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)
        setup()
        reload()
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
            else                 -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera     -> {}
            R.id.nav_gallery    -> {}
            R.id.nav_slideshow  -> {}
            R.id.nav_manage     -> {}
            R.id.nav_share      -> {}
            R.id.nav_send       -> {}
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setup() {
        recycler.layoutManager = LinearLayoutManager(this)
        fab.setOnClickListener {
            if (ZineArticles.list().isEmpty()) {
                Snackbar.make(it, "No articles selected for zine!", Snackbar.LENGTH_LONG).show()
            } else {

            }
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    private fun warnDataLossOrDo(fn: () -> Unit) {
        if (ZineArticles.list().isNotEmpty()) {
            // Pop up data-loss warning
        } else {
            fn()
        }
    }

    //TODO move this stuff out of activity...
    private fun reload() {
        val storage = PocketTokenManager.from(this).storage
        storage.hasAccessToken()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { loggedIn ->
                    if (loggedIn) {
                        storage.storedAccessToken
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    loadArticles(it.accessToken)
                                }
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                }
    }

    private fun loadArticles(token: String) {
        val articles = PocketApiFactory.newApiService().getArticles(
                AuthedRequestBody(Constants.CONSUMER_KEY, token))
        articles.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    recycler.adapter = ArticlesAdapter(it.map)
                }
    }
}
