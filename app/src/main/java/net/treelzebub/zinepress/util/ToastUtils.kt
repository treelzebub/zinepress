package net.treelzebub.zinepress.util

import android.content.Context
import android.widget.Toast

/**
 * Created by Tre Murillo on 1/2/16
 */
object  ToastUtils {

    fun show(context: Context, text: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, text, length).show()
    }
}
