package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.os.Bundle
import net.treelzebub.zinepress.auth.LocalCredStore

/**
 * Created by Tre Murillo on 1/2/16
 */
class MainActivity : BaseAuthActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (LocalCredStore.getToken() == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
