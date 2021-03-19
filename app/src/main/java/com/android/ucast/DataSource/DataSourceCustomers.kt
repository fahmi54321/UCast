package com.android.ucast.DataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.ucast.Model.Customers.DataItem
import com.android.ucast.Network.ConfigApi
import java.lang.Exception

class DataSourceCustomers(val api: ConfigApi) : PagingSource<Int, DataItem>() {
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val nextPage = params.key ?: 1

            val item = api.getCustomer(nextPage, params.loadSize)
            LoadResult.Page(
                    item.data,
                    prevKey =
                    if (nextPage == 1) {
                        null
                    } else {
                        nextPage - 1
                    },
                    nextKey =
                    if (nextPage == item.total) {
                        null

                    } else {
                        nextPage + 1
                    }
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}