package app.appstocks.data.apiservice.utils

import app.appstocks.domain.utils.Result

suspend fun <T> safeApiCall(apiCall: suspend () -> T) = try {
    Result.Success(apiCall.invoke())
} catch (e: Exception) {
    Result.Error(e)
}