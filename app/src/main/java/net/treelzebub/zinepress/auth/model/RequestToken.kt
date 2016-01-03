package net.treelzebub.zinepress.auth.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tre Murillo on 1/2/16
 */
class RequestToken {

    @SerializedName("code")
    val code: String

    constructor(code: String) {
        this.code = code
    }
}
