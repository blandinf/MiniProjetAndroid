package com.blandinf.httpdatas.services

import com.blandinf.httpdatas.models.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("/v2/everything")
    fun getArticlesByCategory(@Query("q") category:String): Call<ArticleResult>
}