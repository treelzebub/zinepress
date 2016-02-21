package net.treelzebub.zinepress.net.api

import com.google.gson.Gson
import net.treelzebub.zinepress.BuildConfig
import net.treelzebub.zinepress.Constants
import retrofit.RestAdapter
import retrofit.converter.GsonConverter

/**
 * Created by Tre Murillo on 1/2/16
 */
object PocketApiFactory {

    private val LOG_LEVEL = if (BuildConfig.DEBUG) {
                                RestAdapter.LogLevel.BASIC
                            } else {
                                RestAdapter.LogLevel.NONE
                            }

    fun newApiService(): PocketApi {
        return RestAdapter.Builder()
                .setConverter(GsonConverter(Gson()))
                .setRequestInterceptor {
                    it.addHeader("Content-type", "application/json")
                }
                .setLogLevel(LOG_LEVEL)
                .setEndpoint(Constants.BASE_URL)
                .build()
                .create(PocketApi::class.java)
    }
}
