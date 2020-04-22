package com.blandinf.miniprojetandroid.services

import com.blandinf.miniprojetandroid.models.SourceResult
import retrofit2.Call
import retrofit2.http.GET

interface SourceService {
    @GET("/v2/sources")
    fun getSources(): Call<SourceResult>
}