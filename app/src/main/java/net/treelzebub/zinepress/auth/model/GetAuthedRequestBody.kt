package net.treelzebub.zinepress.auth.model

/**
 * Created by Tre Murillo on 1/3/16
 */
class GetAuthedRequestBody(accessToken: String, consumerKey: String, val detailType: String) : AuthedRequestBody(accessToken, consumerKey)