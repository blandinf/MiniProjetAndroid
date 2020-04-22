package com.blandinf.httpdatas.services

import com.blandinf.httpdatas.models.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("/v2/sources")
    fun getArticle(@Query("q") q:String): Call<ArticleResult>
}