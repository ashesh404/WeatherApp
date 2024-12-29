package app.appstocks.data.apiservice

import app.appstocks.domain.CurrentDataResponse
import app.appstocks.domain.SearchListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("search.json")
    suspend fun searchApi(@Query("key") key: String = "d563d41c35a349b8938120455242312", @Query("q") q: String): List<SearchListItem>

    @GET("current.json")
    suspend fun currentApi(@Query("key") key: String = "d563d41c35a349b8938120455242312", @Query("q") q: String): CurrentDataResponse

}