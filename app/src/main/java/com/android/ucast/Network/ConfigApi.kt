package com.android.ucast.Network

import com.android.ucast.Model.ResponseData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigApi {
    @GET("v1/players")
    suspend fun getPlayer(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): ResponseData
}