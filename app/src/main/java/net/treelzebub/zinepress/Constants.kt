package net.treelzebub.zinepress

/**
 * Created by Tre Murillo on 1/2/16
 */
object Constants {
    const val CONSUMER_KEY        = "49756-f2c439a8fa6256da0bc3710a"

    // Ex: https://getpocket.com/auth/authorize?request_token=YOUR_REQUEST_TOKEN&redirect_uri=YOUR_REDIRECT_URI
    const val BASE_URL            = "https://getpocket.com/v3"
    const val REQUEST_TOKEN_URL   = "$BASE_URL/oauth/request"
    const val AUTHORIZE_URL       = "$BASE_URL/oauth/authorize"

    const val CALLBACK_URL        = "oauth://zinepress.treelzebub.net"
}
