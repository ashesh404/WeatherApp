/*
package app.appstocks.domain.DI

import app.appstocks.data.api.datasource.ApiRepositoryImpl
import app.appstocks.data.api.datasource.WeatherDataSource
import app.appstocks.domain.repository.ApiRepository
import app.appstocks.domain.usecases.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
*/
/*import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory*//*


@Module()
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideUseCase(repository: ApiRepository): WeatherUseCase {
        return WeatherUseCase(repository)
    }

    @Provides
    fun provideApiRepository(remote: WeatherDataSource.Remote): ApiRepository {
        return ApiRepositoryImpl(remote)
    }
}*/
