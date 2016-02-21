package net.treelzebub.zinepress.util

import android.content.Context
import android.util.Log
import net.treelzebub.zinepress.util.extensions.TAG
import java.io.File

/**
 * Created by Tre Murillo on 2/20/16
 */
object DataUtils {

    fun clearAppData(c: Context) {
        val appDir = File(c.cacheDir.parent)
        var success =
                if (appDir.exists()) {
                    appDir.list().map {
                        if (!it.equals("lib")) {
                            Log.d(TAG, "Deleting File /data/data/${c.packageName}/$it")
                            deleteDir(File(appDir, it))
                        } else false
                    }
                } else listOf(false)
        Log.d(TAG, "AppData clear attempted. Success: ${success.all { it }}")
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            dir.list().forEach {
                val success = deleteDir(File(dir, it))
                if (!success) {
                    return false
                }
            }
        }
        return dir?.delete() ?: false
    }
}