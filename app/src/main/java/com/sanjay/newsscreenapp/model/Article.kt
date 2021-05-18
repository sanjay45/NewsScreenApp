package com.sanjay.newsscreenapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Article(
    val title:String,
    val description:String?,
    @Json(name = "url")
    val webUrl:String,
    @Json(name = "urlToImage")
    val imageUrl:String?
): Parcelable
