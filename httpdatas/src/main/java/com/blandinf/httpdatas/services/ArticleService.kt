package com.blandinf.httpdatas.services

import com.blandinf.httpdatas.models.ArticleResult
import com.blandinf.httpdatas.models.Country
import com.blandinf.httpdatas.models.Source
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("/v2/everything")
    fun getArticlesByCategory(@Query("q") category:String): Call<ArticleResult>

    @GET("/v2/top-headlines")
    fun getArticlesByCountry(@Query("country") country:String): Call<ArticleResult>

    @GET("/v2/top-headlines")
    fun getArticlesBySource(@Query("sources") source:String): Call<ArticleResult>
}