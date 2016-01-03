package net.treelzebub.zinepress.auth

import de.rheinfabrik.heimdall.OAuth2AccessToken

/**
 * Created by Tre Murillo on 1/2/16
 */
class Token : OAuth2AccessToken {
    var token:  String
    var secret: String

    constructor(token: String, secret: String) {
        this.token  = token
        this.secret = secret
    }
}
