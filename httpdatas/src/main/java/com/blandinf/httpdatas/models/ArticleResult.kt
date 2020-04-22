package com.blandinf.httpdatas.models

data class ArticleResult (
    val status: String,
    val totalResult: Int,
    val articles: List<Article>
)