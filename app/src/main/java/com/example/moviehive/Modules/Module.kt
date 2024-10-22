package com.example.moviehive.Modules

import com.example.moviehive.Api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module{
    @Provides
    @Singleton
    fun provideOkHttp():OkHttpClient{
        return OkHttpClient().newBuilder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                val requestWithHeader = request.newBuilder()
                    .header("Authorization" ,
                        "bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ZDllN2ZhZDYwMTJjOWU4ZDVhZDA4NzJjOTQ2YzJlNCIsIm5iZiI6MTcyOTUyNDA2OS42MjMyNzEsInN1YiI6IjY3MTU3NGNmYmQ5MWM4MzgyOWQ3MWU3YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.xr3W0IbwI8AZYdD7N5dnxsLqi8BNei4z50KpJ2BP4gw")
                    .build()
                chain.proceed(requestWithHeader)
            })
            .build()
    }


    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun getNowShowingApi(retrofit: Retrofit): MovieApi{
        return retrofit.create(MovieApi::class.java)
    }
}
