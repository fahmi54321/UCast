package com.android.ucast.DataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.ucast.Model.DataMessage
import com.android.ucast.Network.ConfigApi

class DataSourceMessage(val api: ConfigApi) : PagingSource<Int, DataMessage>() {
    override fun getRefreshKey(state: PagingState<Int, DataMessage>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataMessage> {
        return try {
            val nextPage = params.key ?: 1
            val item = api.getMessage(nextPage, params.loadSize)

            LoadResult.Page(
                    item.data,
                    prevKey = if (nextPage == 1) {
                        null
                    }else {
                        nextPage -1
                    },
                    nextKey =  if (nextPage == item.total) {
                        null
                    }else {
                        nextPage +1
                    }
            )
        }catch (e : Exception) {
            LoadResult.Error(e)

        }
    }

}