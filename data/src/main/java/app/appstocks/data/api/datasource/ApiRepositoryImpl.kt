package app.appstocks.data.api.datasource

import app.appstocks.domain.CurrentDataResponse
import app.appstocks.domain.SearchListItem
import app.appstocks.domain.repository.ApiRepository
import app.appstocks.domain.utils.Result

class ApiRepositoryImpl(var remote: WeatherDataSource.Remote): ApiRepository {
    override suspend fun searchApi(query: String): Result<List<SearchListItem>> {
        return remote.searchApi(query)
    }

    override suspend fun currentApi(query: String): Result<CurrentDataResponse> {
        return remote.currentApi(query)
    }
}