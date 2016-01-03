package net.treelzebub.zinepress.auth.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 1/2/16
 */
class RequestTokenBody : Serializable {

    @SerializedName("consumer_key")
    val clientId: String

    @SerializedName("redirect_uri")
    val redirectUri: String

    @SerializedName("state")
    val state: String?

    constructor(clientId: String, redirectUri: String, state: String? = null) {
        this.clientId       = clientId
        this.redirectUri    = redirectUri
        this.state          = state
    }
}
