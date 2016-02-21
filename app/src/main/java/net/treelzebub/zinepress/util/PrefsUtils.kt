package net.treelzebub.zinepress.util

import android.content.Context
import android.content.SharedPreferences
import net.sarazan.prefs.Pref
import net.sarazan.prefs.SharedPref
import net.treelzebub.zinepress.R
import java.io.File

/**
 * Created by Tre Murillo on 1/2/16
 */
object PrefsUtils {

    fun getPrefs(c: Context = BaseInjection.context): SharedPreferences? {
        return c.applicationContext.getSharedPreferences(c.getString(R.string.key_prefs_file), Context.MODE_PRIVATE)
    }

    fun clearPrefs(c: Context): Boolean {
        getPrefs(c)?.edit()?.clear()?.commit()
        val root = c.filesDir ?: return false
        val dir = File(root.parent + "/shared_prefs/")
        val xml = File(dir, c.getString(R.string.key_prefs_file) + ".xml")
        return xml.delete()
    }

    fun <T> userPref(key: String, clazz: Class<T>): Pref<T> {
        return object : SharedPref<T>(key, clazz) {
            override fun getSharedPreferences(c: Context): SharedPreferences? {
                return getPrefs(c)
            }
        }
    }
}
