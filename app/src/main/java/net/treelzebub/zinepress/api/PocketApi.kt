package net.treelzebub.zinepress.api

import de.rheinfabrik.heimdall.OAuth2AccessToken
import net.treelzebub.zinepress.auth.model.*
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

    @POST("/v3/oauth/request")
    @Headers("Content-Type: application/json", "X-Accept: application/json")
    fun requestToken(@Body body: RequestTokenBody): Observable<RequestToken>

    @POST("/v3/oauth/authorize")
    @Headers("Content-Type: application/json", "X-Accept: application/json")
    fun newAccessToken(@Body body: AccessTokenRequestBody): Observable<OAuth2AccessToken>

    @POST("/v3/oauth/token")
    @Headers("Content-Type: application/json", "X-Accept: application/json")
    fun refreshAccessToken(@Body body: RefreshTokenRequestBody): Observable<OAuth2AccessToken>
}
