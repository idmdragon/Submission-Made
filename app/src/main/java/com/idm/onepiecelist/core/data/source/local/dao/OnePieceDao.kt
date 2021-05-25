package com.idm.onepiecelist.core.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity

@Dao
interface OnePieceDao {
    @Query("SELECT * FROM one_piece")
    fun getAllItems(): LiveData<List<OnePieceEntity>>

    @Query("SELECT * FROM one_piece where isFavorite = 1")
    fun getFavoriteItems(): LiveData<List<OnePieceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(onePiece: List<OnePieceEntity>)

    @Update
    fun updateFavoriteItem(onePiece: OnePieceEntity)

}