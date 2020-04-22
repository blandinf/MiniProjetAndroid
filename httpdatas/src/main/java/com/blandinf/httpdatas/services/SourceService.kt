package com.blandinf.httpdatas.services

import com.blandinf.httpdatas.models.SourceResult
import retrofit2.Call
import retrofit2.http.GET


interface SourceService {
    @GET("/v2/sources")
    fun getSources(): Call<SourceResult>
}