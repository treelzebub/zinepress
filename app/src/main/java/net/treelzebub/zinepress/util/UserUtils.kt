package net.treelzebub.zinepress.util

import android.content.Context

/**
 * Created by Tre Murillo on 2/20/16
 */
object UserUtils {

    fun getName(): String? {
        return PrefsUtils.getPrefs()?.getString("username", null)
    }

    fun saveName(name: String) {
        PrefsUtils.getPrefs()?.edit()?.putString("username", name)?.commit()
    }

    fun logout(c: Context) {
        // Because removing access token, clearing prefs, and deleting DBs was not enough. Sheesh.
        DataUtils.clearAppData(c)
    }
}
