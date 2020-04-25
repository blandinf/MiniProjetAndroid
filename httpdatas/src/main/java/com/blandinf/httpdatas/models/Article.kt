package com.blandinf.httpdatas.models

data class Article(
    val title: String,
    val description: String,
    val author: String,
    val url: String,
    val content:String,
    val source: Source,
    val urlToImage: String,
    val publishedAt:String
)