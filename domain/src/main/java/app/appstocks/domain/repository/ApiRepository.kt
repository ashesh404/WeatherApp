package app.appstocks.domain.repository

import app.appstocks.domain.CurrentDataResponse
import app.appstocks.domain.SearchListItem
import app.appstocks.domain.utils.Result

interface ApiRepository {
    suspend fun searchApi(query: String): Result<List<SearchListItem>>
    suspend fun currentApi(query: String): Result<CurrentDataResponse>
}