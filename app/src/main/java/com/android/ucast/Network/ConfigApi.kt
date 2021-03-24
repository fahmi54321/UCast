package com.android.ucast.Network

import com.android.ucast.Model.ResponseData
import com.android.ucast.Model.ResponseLogin
import com.android.ucast.Model.ResponseMessage
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ConfigApi {
    @GET("v1/players")
    suspend fun getPlayer(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): ResponseData

    @FormUrlEncoded
    @POST("api_login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Flowable<ResponseLogin>

    @GET("message/getMessage")
   suspend fun getMessage(
            @Query("page") page: Int,
            @Query("pageSize") per_page: Int
    ) : ResponseMessage
}