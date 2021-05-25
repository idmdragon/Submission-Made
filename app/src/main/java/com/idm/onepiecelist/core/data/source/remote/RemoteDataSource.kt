package com.idm.onepiecelist.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.idm.onepiecelist.core.data.source.remote.response.OnePieceResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService){

    fun getList(): LiveData<ApiResponse<OnePieceResponse>> {
        val listItem = MutableLiveData<ApiResponse<OnePieceResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getList()
            if (response.isSuccessful) {
                listItem.postValue(response.body()?.let { ApiResponse.Success(it) })
            } else {
                listItem.postValue(
                    response.body()?.let { ApiResponse.Error(response.code().toString()) })
            }
        }
        return listItem
    }
}