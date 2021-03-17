package com.android.ucast.DataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.ucast.Model.DataItem
import com.android.ucast.Network.ConfigApi

class DataSource(var api: ConfigApi) : PagingSource<Int, DataItem>() {
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            var nextPage = params.key ?: 1

            val item = api.getPlayer(nextPage, params.loadSize)

            LoadResult.Page(
                item.data,
                prevKey =
                if (nextPage == 1) { // jika data sama dengan 1 maka data tidak reload secara Previous
                    null
                } else {
                    nextPage - 1 // jika data lebih dari 1 maka data akan reload secara Previous atau mundur (-1)
                },
                nextKey =
                if (nextPage == item.meta?.totalPages) {  // jika data sama dengan total Halaman Database
                    // maka data tidak reload secara next (+1)
                    null

                } else {
                    nextPage + 1  // jika data kecil dari total Halaman Database
                    // maka data tidak reload secara Previous (-1)
                }
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}