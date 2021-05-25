package com.idm.onepiecelist.core.data.source

import androidx.lifecycle.LiveData
import com.idm.onepiecelist.core.data.source.local.LocalDataSource
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import com.idm.onepiecelist.core.data.source.remote.ApiResponse
import com.idm.onepiecelist.core.data.source.remote.RemoteDataSource
import com.idm.onepiecelist.core.data.source.remote.response.OnePieceResponse
import com.idm.onepiecelist.core.utils.DataMapper
import com.idm.onepiecelist.core.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnePieceRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
){
    fun getAllItems(): LiveData<Resource<List<OnePieceEntity>>> =
        object : NetworkBoundResource<List<OnePieceEntity>, OnePieceResponse>() {
            override fun loadFromDB(): LiveData<List<OnePieceEntity>> {
                return localDataSource.getAllItem()
            }

            override fun shouldFetch(data: List<OnePieceEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<OnePieceResponse>> =
                remoteDataSource.getList()

            override fun saveCallResult(data: OnePieceResponse) {
                val itemList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(itemList)
            }
        }.asLiveData()

    fun getFavoriteItems(): LiveData<List<OnePieceEntity>> {
        return localDataSource.getFavoriteItem()
    }

    fun setFavoriteItems(items: OnePieceEntity, state: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteTourism(items, state)
        }

    }
}