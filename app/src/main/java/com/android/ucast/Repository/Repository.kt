package com.android.ucast.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.ucast.DataSource.DataSource
import com.android.ucast.DataSource.DataSourceMessage
import com.android.ucast.Model.DataItem
import com.android.ucast.Model.DataMessage
import com.android.ucast.Model.ResponseLogin
import com.android.ucast.Network.ConfigApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.intellij.lang.annotations.Flow

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
        val pagerMessage = Pager(PagingConfig(pageSize = 10)) {
            DataSourceMessage(api)
        }.flow
        return pagerMessage
    }
}