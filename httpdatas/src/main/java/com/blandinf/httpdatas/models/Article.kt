package com.blandinf.httpdatas.models

data class Article(
    val title: String,
    val description: String,
    val author: String,
    val url: String,
    val content:String,
    val source: Source2,
    val urlToImage: String,
    val publishedAt:String
)

data class Source2(
    val id:String,
    val name:String
)