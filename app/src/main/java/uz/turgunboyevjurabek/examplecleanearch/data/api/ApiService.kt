package uz.turgunboyevjurabek.examplecleanearch.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import uz.turgunboyevjurabek.examplecleanearch.data.dto.GetCatsDto

interface ApiService {
    @GET("search?")
    suspend fun getCatsApiService(
        @Query("limit") limit:Int=10
    ): List<GetCatsDto>
}