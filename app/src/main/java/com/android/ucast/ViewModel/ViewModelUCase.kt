package com.android.ucast.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.ucast.Model.DataItem
import com.android.ucast.Repository.Repository
import kotlinx.coroutines.flow.Flow

class ViewModelUCase(var repository: Repository) : ViewModel() {

    var responPlayer: kotlinx.coroutines.flow.Flow<PagingData<DataItem>>? = null
    var responCustomers: Flow<PagingData<com.android.ucast.Model.Customers.DataItem>>? = null

    fun setData() {
        responPlayer = repository.getData().cachedIn(viewModelScope)
    }

    fun getData() = responPlayer

    fun setDataCustomers() {
        responCustomers = repository.getCustomers().cachedIn(viewModelScope)
    }

    fun getDataCustomers() = responCustomers
}