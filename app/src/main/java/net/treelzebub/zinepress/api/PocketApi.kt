package net.treelzebub.zinepress.api

import de.rheinfabrik.heimdall.OAuth2AccessToken
import net.treelzebub.zinepress.auth.model.AccessTokenRequestBody
import net.treelzebub.zinepress.auth.model.RefreshTokenRequestBody
import retrofit.http.Body
import retrofit.http.POST
import rx.Observable

/**
 * Created by Tre Murillo on 1/2/16
 */
interface PocketApi {

    @POST("/oauth/token")
    fun grantNewAccessToken(@Body body: AccessTokenRequestBody): Observable<OAuth2AccessToken>

    @POST("/oauth/token")
    fun refreshAccessToken(@Body body: RefreshTokenRequestBody): Observable<OAuth2AccessToken>
}
