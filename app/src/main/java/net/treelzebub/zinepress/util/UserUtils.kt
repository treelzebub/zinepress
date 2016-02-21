package net.treelzebub.zinepress.util

import android.content.Context
import net.treelzebub.zinepress.auth.PocketTokenManager

/**
 * Created by Tre Murillo on 2/20/16
 */
object UserUtils {

    fun getName(): String {
        val default = "Pocket User"
        return PrefsUtils.getPrefs()?.getString("username", default) ?: default
    }

    fun saveName(name: String) {
        PrefsUtils.getPrefs()?.edit()?.putString("username", name)?.commit()
    }

    fun logout(c: Context) {
        PocketTokenManager.from(c).storage.removeAccessToken()
        PrefsUtils.clearPrefs(c)
    }
}