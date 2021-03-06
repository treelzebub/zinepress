package net.treelzebub.zinepress.net.api

import de.rheinfabrik.heimdall.OAuth2AccessToken
import net.treelzebub.zinepress.net.api.model.PocketArticleResponse
import net.treelzebub.zinepress.auth.model.*
import net.treelzebub.zinepress.auth.model.PocketAccessToken
import retrofit.http.Body
import retrofit.http.Headers
import retrofit.http.POST
import rx.Observable

/**
 * Created by Tre Murillo on 1/2/16
 *
 * All Pocket API calls must be POST. Pocket does not accept GET calls.
 */
interface PocketApi {

    // Auth Flow
    @POST("/v3/oauth/request")
    @Headers("Content-Type: application/json", "X-Accept: application/json")
    fun requestToken(@Body body: RequestTokenBody): Observable<RequestToken>

    @POST("/v3/oauth/authorize")
    @Headers("Content-Type: application/json", "X-Accept: application/json")
    fun newAccessToken(@Body body: AccessTokenRequestBody): Observable<PocketAccessToken>

    @POST("/v3/oauth/token")
    @Headers("Content-Type: application/json", "X-Accept: application/json")
    fun refreshAccessToken(@Body body: RefreshTokenRequestBody): Observable<OAuth2AccessToken>

    // Authed Requests
    @POST("/v3/get")
    @Headers("Content-Type: application/json", "X-Accept: application/json")
    fun getArticles(@Body body: AuthedRequestBody): Observable<PocketArticleResponse>
}
