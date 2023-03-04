package com.example.myapplication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDataClass(
    @Json(name ="id")
    val id: String,
    @Json(name ="title")
    val title: String,
    @Json(name ="poster_path")
    val posterImage: String
)

@JsonClass(generateAdapter = true)
data class ResultDataClass(
    @Json(name ="page")
    val page: Int,
    @Json(name ="results")
    val results: List<MovieDataClass>,
    @Json(name ="dates")
    val dates: Date,
    @Json(name ="total_pages")
    val total_pages: Int,
    @Json(name ="total_results")
    val total_results: Int
)
@JsonClass(generateAdapter = true)
data class Date(
    @Json(name ="maximum")
    val maximum : String,
    @Json(name ="minimum")
    val minimum: String
)
//{
//    "poster_path":"/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
//    "adult":false,
//    "overview":"From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
//    "release_date":"2016-08-03",
//    "genre_ids":[
//    14,
//    28,
//    80
//    ],
//    "id":297761,
//    "original_title":"Suicide Squad",
//    "original_language":"en",
//    "title":"Suicide Squad",
//    "backdrop_path":"/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg",
//    "popularity":48.261451,
//    "vote_count":1466,
//    "video":false,
//    "vote_average":5.91
//},