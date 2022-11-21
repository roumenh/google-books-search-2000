package com.example.gogglebookssearch.network


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Book(
    @Json(name = "items")
    val items: List<Item>,
    @Json(name = "kind")
    val kind: String,
    @Json(name = "totalItems")
    val totalItems: Int
) {
    @JsonClass(generateAdapter = true)
    data class Item(
        @Json(name = "etag")
        val etag: String,
        @Json(name = "id")
        val id: String,
        @Json(name = "kind")
        val kind: String,
        @Json(name = "saleInfo")
        val saleInfo: SaleInfo? = null,
        @Json(name = "volumeInfo")
        val volumeInfo: VolumeInfo

    ) {

        @JsonClass(generateAdapter = true)
        data class SaleInfo(
            @Json(name = "buyLink")
            val buyLink: String? = null,
            @Json(name = "country")
            val country: String? = null,
            @Json(name = "listPrice")
            val listPrice: ListPrice? = null,
        ) {
            @JsonClass(generateAdapter = true)
            data class ListPrice(
                @Json(name = "amount")
                val amount: Double,
                @Json(name = "currencyCode")
                val currencyCode: String
            )
        }

        @JsonClass(generateAdapter = true)
        data class VolumeInfo(
            @Json(name = "allowAnonLogging")
            val allowAnonLogging: Boolean,
            @Json(name = "authors")
            val authors: List<String>? = null,
            @Json(name = "canonicalVolumeLink")
            val canonicalVolumeLink: String,
            @Json(name = "contentVersion")
            val contentVersion: String,
            @Json(name = "description")
            val description: String? = null,
            @Json(name = "imageLinks")
            val imageLinks: ImageLinks? = null,
            @Json(name = "infoLink")
            val infoLink: String,
            @Json(name = "maturityRating")
            val maturityRating: String,
            @Json(name = "publishedDate")
            val publishedDate: String? = null,
            @Json(name = "title")
            val title: String
        ) {
            @JsonClass(generateAdapter = true)
            data class ImageLinks(
                @Json(name = "smallThumbnail")
                val smallThumbnail: String,
                @Json(name = "thumbnail")
                val thumbnail: String
            )

        }
    }
}