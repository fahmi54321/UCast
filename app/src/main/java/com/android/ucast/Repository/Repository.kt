package com.android.ucast.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.ucast.DataSource.DataSource
import com.android.ucast.Model.DataItem
import com.android.ucast.Network.ConfigApi

class Repository(var api: ConfigApi) {

    fun getData(): kotlinx.coroutines.flow.Flow<PagingData<DataItem>> {
        val pager = Pager(PagingConfig(pageSize = 10)) {
            DataSource(api)
        }.flow
        return pager
    }
}