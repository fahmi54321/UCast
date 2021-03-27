package com.android.ucast.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.ucast.DataSource.DataSource
import com.android.ucast.DataSource.DataSourceMessage
import com.android.ucast.Model.*
import com.android.ucast.Network.ConfigApi
import com.google.gson.annotations.Until
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow


class Repository(var api: ConfigApi) {

    fun getData(): kotlinx.coroutines.flow.Flow<PagingData<DataItem>> {
        val pager = Pager(PagingConfig(pageSize = 10)) {
            DataSource(api)
        }.flow
        return pager
    }

    fun loginUser(
            email: String,
            password: String,
            responseSuccess: (ResponseLogin) -> Unit,
            responseError: (Throwable) -> Unit
    ) {
        api.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    responseSuccess(it)
                }, {
                    responseError(it)
                })
    }

    fun getDataMessage(): kotlinx.coroutines.flow.Flow<PagingData<DataMessage>> {
        val pager = Pager(PagingConfig(pageSize = 5)) {
            DataSourceMessage(api)
        }.flow
        return pager
    }

    fun InsertMessage(
            title: String,
            content: String,
            userId: String,
            successResponse: (ResponseInsertMessage) -> Unit,
            errorResponse: (Throwable) -> Unit
    ) {
        api.InsertMessage(title, content, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    successResponse(it)
                }, {
                    errorResponse(it)
                })
    }

    fun deleteMessage(
            id_message: Int,
            onSuccess: (ResponseDeleteMessage) -> Unit,
            onError: (Throwable) -> Unit
    ) {
        api.deleteMessage(id_message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onSuccess(it)
                }, {
                    onError(it)
                })
    }

    fun updateMessage(
            title: String,
            content: String,
            userId: String,
            id_message: Int,
            isSuccess: (ResponseUpdateMessage) -> Unit,
            isError: (Throwable) -> Unit
    ) {
        api.updateMessage(title, content, userId, id_message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isSuccess(it)
                }, {
                    isError(it)
                })
    }


}