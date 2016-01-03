package net.treelzebub.zinepress.auth

import com.squareup.okhttp.OkHttpClient
import retrofit.RestAdapter
import retrofit.client.OkClient

/**
 * Created by Tre Murillo on 1/2/16
 */
object ServiceGenerator {

    private val builder = RestAdapter.Builder()

    fun <S> createService(serviceClazz: Class<S>, baseUrl: String, accessToken: Token): S {
        val client = OkHttpClient()
        builder.setClient(OkClient(client))
        builder.setEndpoint(baseUrl).setLogLevel(RestAdapter.LogLevel.FULL)
        builder.setRequestInterceptor {
            it.addHeader("Accept", "application/json")
            it.addHeader("Authorization", accessToken.token /*TODO*/)
        }
        val adapter = builder.build()
        return adapter.create(serviceClazz)
    }
}
