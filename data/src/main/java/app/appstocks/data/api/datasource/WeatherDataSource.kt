package app.appstocks.data.api.datasource

import app.appstocks.domain.CurrentDataResponse
import app.appstocks.domain.SearchListItem
import app.appstocks.domain.utils.Result

interface WeatherDataSource {
    interface Remote {
        suspend fun searchApi(query: String): Result<List<SearchListItem>>
        suspend fun currentApi(query: String): Result<CurrentDataResponse>
    }

    interface Local {

    }
}