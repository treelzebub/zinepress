package net.treelzebub.zinepress

/**
 * Created by Tre Murillo on 1/2/16
 */
object Constants {
    const val CONSUMER_KEY        = "treelzebub"
    const val CONSUMER_SECRET     = "qMnTRpLAntrDC3f3xJyeHR6WwUWmCXYU"

    const val BASE_URL            = "https://www.readability.com/api/rest/v1"
    const val AUTHORIZE_URL       = BASE_URL + "/oauth/authorize"
    const val REQUEST_TOKEN_URL   = BASE_URL + "/oauth/request_token"
    const val ACCESS_TOKEN_URL    = BASE_URL + "/oauth/access_token"

    const val ENCODING            = "UTF-8"
    const val SIGNATURE_METHOD    = "HMAC-SHA1"
    const val CALLBACK_URL        = "oauth://readerbility.treelzebub.net"

    const val CONTENT_TYPE        = "application/x-www-form-urlencoded"
}
