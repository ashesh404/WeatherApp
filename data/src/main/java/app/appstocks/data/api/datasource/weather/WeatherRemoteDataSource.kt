package app.appstocks.data.api.datasource.weather

import app.appstocks.data.api.datasource.WeatherDataSource
import app.appstocks.data.apiservice.utils.safeApiCall
import app.appstocks.domain.CurrentDataResponse
import app.appstocks.domain.SearchListItem
import app.appstocks.data.apiservice.WeatherApi
import app.appstocks.domain.utils.Result

class WeatherRemoteDataSource(val api: WeatherApi): WeatherDataSource.Remote {
    override suspend fun searchApi(query: String): Result<List<SearchListItem>> = safeApiCall {
        api.searchApi(q = query)
    }

    override suspend fun currentApi(query: String): Result<CurrentDataResponse> = safeApiCall {
        api.currentApi(q = query)
    }
}