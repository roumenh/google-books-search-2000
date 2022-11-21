package com.example.gogglebookssearch.network

import com.example.gogglebookssearch.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/books/v1/"
private const val API_KEY = BuildConfig.apiKey

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookApiService {
    //"volumes?q=inauthor:NAMEOFAUTHOR&projection=lite&key="+API_KEY
    @GET("volumes?projection=lite&langRestrict=cs&maxResults=40&key="+API_KEY)
    suspend fun getBooks(
        @Query("q") query: String
    ) : Book

}

object BookApi {
    val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
}
}