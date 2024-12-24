package app.appstocks.weatherapp.repository.apiservice

import app.appstocks.weatherapp.models.response.CurrentDataResponse
import app.appstocks.weatherapp.models.response.SearchList
import app.appstocks.weatherapp.models.response.SearchListItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("search.json")
    fun searchApi(@Query("key") key: String = "d563d41c35a349b8938120455242312", @Query("q") q: String): Call<List<SearchListItem>>

    @GET("current.json")
    fun currentApi(@Query("key") key: String = "d563d41c35a349b8938120455242312", @Query("q") q: String): Call<CurrentDataResponse>

}