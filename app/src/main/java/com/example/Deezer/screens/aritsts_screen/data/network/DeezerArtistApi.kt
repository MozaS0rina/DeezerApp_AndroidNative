package com.example.Deezer.screens.aritsts_screen.data

import com.example.Deezer.screens.aritsts_screen.model.ArtistsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerArtistApi {
    @GET("genre/{genre_id}/artists")//adnotatie retrofit pt f. GET, apel de endpoint
    suspend fun getArtists(@Path("genre_id") genreId: String): Response<ArtistsResponse>

}
object DeezerArtistApiHelper {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.deezer.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(DeezerArtistApi::class.java)//creeaza o implementare a endpointului

    @Throws(RuntimeException::class)
    suspend fun fetchArtists(genreId: String): ArtistsResponse {
        val response = api.getArtists(genreId)
        if (response.isSuccessful) {
            return response.body() ?: throw RuntimeException("No data")
        } else {
            throw RuntimeException("API call failed: ${response.errorBody()?.string()}")
        }
    }
}