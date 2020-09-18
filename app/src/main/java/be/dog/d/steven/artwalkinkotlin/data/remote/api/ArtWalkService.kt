package be.dog.d.steven.artwalkinkotlin.data.remote.api

import be.dog.d.steven.artwalkinkotlin.model.ArtWork
import retrofit2.Response
import retrofit2.http.GET

interface ArtWalkService {

    @GET("/api/records/1.0/search/?dataset=comic-book-route&rows=58")
    suspend fun getArtWorks(): Response<List<ArtWork>>

    companion object {
        const val FOODIUM_API_URL = "https://bruxellesdata.opendatasoft.com/"
    }
}