package net.treelzebub.zinepress.auth.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tre Murillo on 1/2/16
 */
open class AuthedRequestBody {

    @SerializedName("consumer_key")
    val consumerKey: String

    @SerializedName("access_token")
    val accessToken: String

    constructor(consumerKey: String, accessToken: String) {
        this.consumerKey = consumerKey
        this.accessToken = accessToken
    }

}
