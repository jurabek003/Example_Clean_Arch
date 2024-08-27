package uz.turgunboyevjurabek.examplecleanearch.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.turgunboyevjurabek.examplecleanearch.data.api.ApiService
import uz.turgunboyevjurabek.examplecleanearch.data.repositoryImpl.MyRepositoryImpl
//import uz.turgunboyevjurabek.examplecleanearch.data.repositoryImpl.MyRepositoryImpl
import uz.turgunboyevjurabek.examplecleanearch.domein.repozotory.MyRepository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/images/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getClient():OkHttpClient=OkHttpClient.Builder()
        .connectTimeout(60,TimeUnit.SECONDS)
        .readTimeout(60,TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(Interceptor { chain ->
            val builder=chain.request().newBuilder()
            builder.addHeader("x-api-key","live_SRA8uWQaTkJs6pTxgpVEr3WCpXbAG5kWQKrDPBOjQyXwtPFbpC2UYAtWxz1hYljo")
            chain.proceed(builder.build())
        })
        .build()



//    @Singleton
//    @Provides
//    fun createRequest(): Request {
//        return Request.Builder()
//            .url("https://api-football-v1.p.rapidapi.com/v3/leagues")
//            .get()
//            .addHeader("x-rapidapi-key", "8ed42071e3msh8f43f66834e0740p1151d0jsn793a98e19001")
//            .addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
//            .build()
//    }

//    @Singleton
//    @Provides
//    fun executeRequest(okHttpClient: OkHttpClient, request: Request): Response {
//        return okHttpClient.newCall(request).execute()
//    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMyRepository(apiService: ApiService): MyRepository {
        return MyRepositoryImpl(apiService)
    }

}
