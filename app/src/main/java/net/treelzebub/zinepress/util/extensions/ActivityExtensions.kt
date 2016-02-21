package net.treelzebub.zinepress.util.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Created by Tre Murillo on 2/20/16
 */

inline fun <reified T : Activity> Activity.createIntent(b: Bundle = Bundle.EMPTY): Intent {
    return Intent(this, T::class.java).apply { putExtras(b) }
}
