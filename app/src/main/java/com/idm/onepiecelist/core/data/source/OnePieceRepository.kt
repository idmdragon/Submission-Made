package com.idm.onepiecelist.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.idm.onepiecelist.core.data.source.local.LocalDataSource
import com.idm.onepiecelist.core.data.source.remote.ApiResponse
import com.idm.onepiecelist.core.data.source.remote.RemoteDataSource
import com.idm.onepiecelist.core.data.source.remote.response.OnePieceResponse
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.core.domain.repository.IOnePieceRepository
import com.idm.onepiecelist.core.utils.DataMapper
import com.idm.onepiecelist.core.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnePieceRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IOnePieceRepository{
    override fun getAllItems(): LiveData<Resource<List<OnePiece>>> =
        object : NetworkBoundResource<List<OnePiece>, OnePieceResponse>() {
            override fun loadFromDB(): LiveData<List<OnePiece>> {
                return Transformations.map(localDataSource.getAllItem()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<OnePiece>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<OnePieceResponse>> =
                remoteDataSource.getList()

            override fun saveCallResult(data: OnePieceResponse) {
                val itemList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(itemList)
            }
        }.asLiveData()

    override fun getFavoriteItems(): LiveData<List<OnePiece>> {
        return Transformations.map(localDataSource.getFavoriteItem()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteItems(items: OnePiece, state: Boolean) {
        val onePieceEntity = DataMapper.mapDomainToEntity(items)

        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteTourism(onePieceEntity, state)
        }

    }
}