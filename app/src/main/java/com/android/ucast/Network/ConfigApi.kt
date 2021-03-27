package com.android.ucast.Network

import com.android.ucast.Model.*
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

   @FormUrlEncoded
   @POST("message/insertMessage")
   fun InsertMessage(
           @Field("title") title: String,
           @Field("content") content: String,
           @Field("userId") userId: String
   ): Single<ResponseInsertMessage>

   @FormUrlEncoded
   @POST("message/deleteMessage")
   fun deleteMessage(
           @Field("id_message") id_message: Int
   ): Single<ResponseDeleteMessage>

   @FormUrlEncoded
   @POST("message/updateMessage")
   fun updateMessage(
           @Field("title") title: String,
           @Field("content") content: String,
           @Field("userId") userId: String,
           @Field("id_message") id_message: Int
   ): Single<ResponseUpdateMessage>
}