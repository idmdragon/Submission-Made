package com.idm.onepiecelist.core.data.source.local

import androidx.lifecycle.LiveData
import com.idm.onepiecelist.core.data.source.local.dao.OnePieceDao
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val onePieceDao: OnePieceDao
){

    fun getAllItem(): LiveData<List<OnePieceEntity>> = onePieceDao.getAllItems()

    fun getFavoriteItem(): LiveData<List<OnePieceEntity>> = onePieceDao.getFavoriteItems()

    fun insertTourism(item: List<OnePieceEntity>) = onePieceDao.insertItem(item)

    fun setFavoriteTourism(item: OnePieceEntity, newState: Boolean) {
        item.isFavorite = newState
        onePieceDao.updateFavoriteItem(item)
    }
}