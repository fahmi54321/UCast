package com.android.ucast.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.ucast.Model.*
import com.android.ucast.Repository.Repository
import kotlinx.coroutines.flow.Flow

class ViewModelUCase(var repository: Repository) : ViewModel() {
    //Login
    val isSuccess = MutableLiveData<ResponseLogin>()
    val isError = MutableLiveData<Throwable>()

    //Insert
    val successResponse = MutableLiveData<ResponseInsertMessage>()

    //delete
    val onSuccess = MutableLiveData<ResponseDeleteMessage>()

    //update
    val successOn = MutableLiveData<ResponseUpdateMessage>()

    var responMessage: Flow<PagingData<DataMessage>>? = null
    var responPlayer: kotlinx.coroutines.flow.Flow<PagingData<DataItem>>? = null

    fun setData() {
        responPlayer = repository.getData().cachedIn(viewModelScope)
    }

    fun getData() = responPlayer

    fun dataLogin(
            email: String,
            password: String
    ) {
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

    fun insertMessage(
            title: String,
            content: String,
            userId: String
    ) {
        repository.InsertMessage(title, content, userId, {
            successResponse.value = it
        }, {
            isError.value = it
        })
    }

    fun deleteMessage(
            id_message: Int
    ) {
        repository.deleteMessage(id_message, {
            onSuccess.value = it
        }, {
            isError.value = it
        })
    }

    fun updateMessage(
            title: String,
            content: String,
            userId: String,
            id_message: Int
    ) {
        repository.updateMessage(title, content, userId, id_message, {
            successOn.value = it
        }, {
            isError.value = it
        })
    }

    fun successOn(): LiveData<ResponseUpdateMessage>{
        return successOn
    }

    fun onSuccess(): LiveData<ResponseDeleteMessage> {
        return onSuccess
    }


    fun successResponse(): LiveData<ResponseInsertMessage> {
        return successResponse
    }

    fun isSuccess(): LiveData<ResponseLogin> {
        return isSuccess
    }

    fun isError(): LiveData<Throwable> {
        return isError
    }

}