package net.treelzebub.zinepress.api

import com.google.gson.Gson
import net.treelzebub.zinepress.Constants
import retrofit.RestAdapter
import retrofit.converter.GsonConverter

/**
 * Created by Tre Murillo on 1/2/16
 */
object PocketApiFactory {

    fun newApiService(): PocketApi {
        val restAdapterBuilder = RestAdapter.Builder()
                .setConverter(GsonConverter(Gson()))
                .setRequestInterceptor {
                    it.addHeader("Content-type", "application/json")
                }
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.BASE_URL)
        return restAdapterBuilder.build().create(PocketApi::class.java)
    }
}
