package app.appstocks.domain.usecases

import app.appstocks.domain.repository.ApiRepository

class WeatherUseCase constructor(private val repository: ApiRepository) {
    suspend fun searchQuery(query: String)  = repository.searchApi(query)
    suspend fun currentApi(query: String) = repository.currentApi(query)
}