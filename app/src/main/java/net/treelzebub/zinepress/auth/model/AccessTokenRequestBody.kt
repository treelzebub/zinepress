package net.treelzebub.zinepress.auth.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 1/2/16
 *
 * Body object used to exchange a code with an access token.
 */
class AccessTokenRequestBody : Serializable {
    @SerializedName("code")
    val code: String

    @SerializedName("consumer_key")
    val consumerKey: String

    @SerializedName("redirect_uri")
    val redirectUri: String

    constructor(code: String, consumerKey: String, redirectUri: String) {
        this.code        = code
        this.consumerKey = consumerKey
        this.redirectUri = redirectUri
    }
}
