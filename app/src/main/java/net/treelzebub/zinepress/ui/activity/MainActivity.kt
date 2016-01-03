package net.treelzebub.zinepress.ui.activity

import android.content.Intent
import android.os.Bundle

/**
 * Created by Tre Murillo on 1/2/16
 */
class MainActivity : BaseRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO check for existing token
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
