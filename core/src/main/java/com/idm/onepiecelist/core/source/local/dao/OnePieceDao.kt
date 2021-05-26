package com.idm.onepiecelist.core.source.local.dao

import androidx.room.*
import androidx.room.Dao
import com.idm.onepiecelist.core.source.local.entity.OnePieceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OnePieceDao {
    @Query("SELECT * FROM one_piece")
    fun getAllItems(): Flow<List<OnePieceEntity>>

    @Query("SELECT * FROM one_piece where isFavorite = 1")
    fun getFavoriteItems(): Flow<List<OnePieceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(onePiece: List<OnePieceEntity>)

    @Update
    fun updateFavoriteItem(onePiece: OnePieceEntity)

}