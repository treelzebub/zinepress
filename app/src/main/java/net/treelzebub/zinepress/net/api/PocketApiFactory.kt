package net.treelzebub.zinepress.net.api

import com.google.gson.Gson
import net.treelzebub.zinepress.Constants
import net.treelzebub.zinepress.auth.model.AuthedRequestBody
import retrofit.RestAdapter
import retrofit.converter.GsonConverter

/**
 * Created by Tre Murillo on 1/2/16
 */
object PocketApiFactory {

    fun newApiService(): PocketApi {
        return RestAdapter.Builder()
                .setConverter(GsonConverter(Gson()))
                .setRequestInterceptor {
                    it.addHeader("Content-type", "application/json")
                }
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.BASE_URL)
                .build()
                .create(PocketApi::class.java)
    }

    fun articlesRequestBody(token: String): AuthedRequestBody {
        return AuthedRequestBody(Constants.CONSUMER_KEY, token)
    }
}