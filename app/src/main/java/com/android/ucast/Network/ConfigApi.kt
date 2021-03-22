package com.android.ucast.Network

import com.android.ucast.Model.Customers.ResponseCustomers
import com.android.ucast.Model.Messagess.ResponseMessages
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

    @GET("customer/getCustomer")
    suspend fun getCustomer(
            @Query("page") page: Int,
            @Query("pageSize") per_page: Int
    ): ResponseCustomers

    @GET("customer/getCustomer")
    fun getCustomers(
            @Query("page") page: Int,
            @Query("pageSize") per_page: Int
    ): Flowable<ResponseCustomers>

    @GET("message/getMessage")
    suspend fun getMessage(
            @Query("page") page: Int,
            @Query("pageSize") per_page: Int
    ): ResponseMessages

}