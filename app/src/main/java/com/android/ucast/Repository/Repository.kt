package com.android.ucast.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.ucast.DataSource.DataSource
import com.android.ucast.DataSource.DataSourceCustomers
import com.android.ucast.DataSource.DataSourceMessages
import com.android.ucast.Model.DataItem
import com.android.ucast.Model.Messagess.DataItemMessages
import com.android.ucast.Network.ConfigApi
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

class Repository(var api: ConfigApi) {

    fun getData(): kotlinx.coroutines.flow.Flow<PagingData<DataItem>> {
        val pager = Pager(PagingConfig(pageSize = 10)) {
            DataSource(api)
        }.flow
        return pager
    }

    fun getCustomers(): Flow<PagingData<com.android.ucast.Model.Customers.DataItem>> {
        val pager = Pager(PagingConfig(pageSize = 10)) {
            DataSourceCustomers(api)
        }.flow

        return pager
    }

    fun getMessages(): Flow<PagingData<DataItemMessages>> {
        val pager = Pager(PagingConfig(pageSize = 10)) {
            DataSourceMessages(api)
        }.flow

        return pager
    }
}