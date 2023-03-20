package com.rijksmuseum.data.interceptors

import com.rijksmuseum.data.datasource.local.RijksmuseumLocalDatasource
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestInterceptor(
    private val localDatasource: RijksmuseumLocalDatasource,
    private val apiKey: String
): Interceptor {

    companion object {
        private const val API_KEY_KEY = "key"
        private const val CULTURE_KEY = "{culture}"
    }

    private val params = mapOf(
        API_KEY_KEY to apiKey
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .addCulture(localDatasource.getCulture())
            .addQueryParamsIfNeeded(
                params
            ).let {
                chain.proceed(it)
            }
    }

    private fun Request.addQueryParamsIfNeeded(parametersMap: Map<String, String>): Request {
        val newUrl = url.newBuilder().apply {
            parametersMap.forEach { entry ->
                addQueryParameter(name = entry.key, value = entry.value)
            }
        }.build()
        return newBuilder().url(newUrl).build()
    }

    private fun Request.addCulture(culture: String): Request {
        return newBuilder()
            .url(url.toString().replace(CULTURE_KEY, culture).toHttpUrl())
            .build()
    }
}
