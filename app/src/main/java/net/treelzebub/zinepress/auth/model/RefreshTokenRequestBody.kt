package net.treelzebub.zinepress.auth.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 1/2/16
 * 
 * Body used to refresh an access token.
 */
class RefreshTokenRequestBody : Serializable {

    @SerializedName("refresh_token")
    val refreshToken: String

    @SerializedName("consumer_key")
    val clientId: String

    @SerializedName("redirect_uri")
    val redirectUri: String

    @SerializedName("grant_type")
    val grantType: String

    constructor(refreshToken: String, clientId: String, redirectUri: String, grantType: String) {
        this.refreshToken   = refreshToken
        this.clientId       = clientId
        this.redirectUri    = redirectUri
        this.grantType      = grantType
    }
}