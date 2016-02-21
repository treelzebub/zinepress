package net.treelzebub.zinepress.net.api

import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.auth.model.AuthedRequestBody

/**
 * Created by Tre Murillo on 2/20/16
 */
object Pocket {

    fun articlesRequestBody(token: String): AuthedRequestBody {
        return AuthedRequestBody(Constants.CONSUMER_KEY, token)
    }
}