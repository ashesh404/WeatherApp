package app.appstocks.data.DI

import app.appstocks.data.api.datasource.ApiRepositoryImpl
import app.appstocks.data.api.datasource.WeatherDataSource
import app.appstocks.data.api.datasource.weather.WeatherRemoteDataSource
import app.appstocks.data.apiservice.RetrofitInstance
import app.appstocks.data.apiservice.WeatherApi
import app.appstocks.domain.repository.ApiRepository
import app.appstocks.domain.usecases.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module()
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client =  OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        var retrofitBuilder =  Retrofit.Builder()
            .baseUrl(RetrofitInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofitBuilder
    }

    @Provides
    fun providesApiService(build: Retrofit): WeatherApi {
        val apiService: WeatherApi by lazy {
            build
                .create(WeatherApi::class.java)
        }
        return apiService
    }

    @Provides
    fun providesDataSource(api: WeatherApi): WeatherDataSource.Remote {
        return WeatherRemoteDataSource(api)
    }

    @Provides
    fun provideUseCase(repository: ApiRepository): WeatherUseCase {
        return WeatherUseCase(repository)
    }

    @Provides
    fun provideApiRepository(remote: WeatherDataSource.Remote): ApiRepository {
        return ApiRepositoryImpl(remote)
    }
}