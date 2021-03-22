package com.android.ucast.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.ucast.Model.DataItem
import com.android.ucast.Model.DataMessage
import com.android.ucast.Model.ResponseLogin
import com.android.ucast.Repository.Repository
import kotlinx.coroutines.flow.Flow

class ViewModelUCase(var repository: Repository):ViewModel() {
    val isSuccess = MutableLiveData<ResponseLogin>()
    val isError = MutableLiveData<Throwable>()

    var responMessage: Flow<PagingData<DataMessage>>? = null
    var responPlayer: kotlinx.coroutines.flow.Flow<PagingData<DataItem>>? = null

    fun setData() {
        responPlayer = repository.getData().cachedIn(viewModelScope)
    }

    fun getData() = responPlayer

    fun dataLogin(
        email: String,
        password: String
    ){
        repository.loginUser(email, password, {
             isSuccess.value = it
        }, {
            isError.value = it
        })

    }

    fun setMessage() {
        responMessage = repository.getDataMessage().cachedIn(viewModelScope)
    }

    fun getMessage() = responMessage

    fun isSuccess(): LiveData<ResponseLogin> {
        return isSuccess
    }

    fun isError(): LiveData<Throwable>{
        return isError
    }

}