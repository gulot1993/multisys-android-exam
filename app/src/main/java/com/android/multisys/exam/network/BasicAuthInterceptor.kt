package com.android.multisys.exam.network

import android.content.SharedPreferences
import com.android.multisys.exam.BuildConfig
import okhttp3.Credentials
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class BasicAuthInterceptor @Inject constructor(
    val sharedPreferences: SharedPreferences
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val credentials = Credentials.basic(BuildConfig.REDDIT_CLIENT_ID,"")
        val request = chain.request()
        val headersBuilder = Headers.Builder()
        headersBuilder.add("Authorization", credentials)
        headersBuilder.add("User-Agent", BuildConfig.USER_AGENT)

        val token = sharedPreferences.getString("token", null)
        if (token != null) {
            Timber.d("I was here $token")
            headersBuilder.add("Authorization", "bearer $token")
        }
        val headers = headersBuilder.build()
        val newRequest = request.newBuilder()
            .headers(headers)
            .build()

        newRequest.headers.forEach {
            Timber.d("header each: $it")
        }
        return chain.proceed(newRequest)
    }
}