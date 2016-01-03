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

    @SerializedName("client_id")
    val clientId: String

    @SerializedName("redirect_uri")
    val redirectUri: String

    @SerializedName("grant_type")
    val grantType: String

    // Constructor

    constructor(code: String, clientId: String, redirectUri: String, grantType: String) {
        this.code           = code
        this.clientId       = clientId
        this.redirectUri    = redirectUri
        this.grantType      = grantType
    }
}
