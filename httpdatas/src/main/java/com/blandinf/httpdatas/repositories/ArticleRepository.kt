package com.blandinf.httpdatas.repositories

import android.os.Build
import com.blandinf.httpdatas.BuildConfig
import com.blandinf.httpdatas.models.Article
import com.blandinf.httpdatas.models.Source
import com.blandinf.httpdatas.services.ArticleService
import com.blandinf.httpdatas.services.SourceService
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


class ArticleRepository {
    private val service: ArticleService
    val apiKey: String = BuildConfig.ApiKey;

    fun getArticlesByCategory(category:String): List<Article> {
        val response = service.getArticlesByCategory(category).execute()
        return response.body()?.articles ?: emptyList()
    }
    fun getArticlesByCountry(country:String): List<Article> {
        val response = service.getArticlesByCountry(country).execute()
        return response.body()?.articles ?: emptyList()
    }

    fun getArticlesBySources(source:String): List<Article> {
        val response = service.getArticlesBySource(source).execute()
        return response.body()?.articles ?: emptyList()
    }

    private val requestInterceptor: Interceptor = Interceptor { chain ->
        val original: Request = chain.request()

        val url: HttpUrl = original.url
            .newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        val device = Build.MANUFACTURER + "-" + Build.MODEL
        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .addHeader("User-Agent", "Android-${BuildConfig.VERSION_CODE}-($device)")
            .addHeader("Accept-Language", Locale.getDefault().language)
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .url(url)

        val request = requestBuilder.build()
        return@Interceptor chain.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .addInterceptor(requestInterceptor)
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    init {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org")
        }.addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
            .build()

        service = retrofit.create(ArticleService::class.java)
    }
}