package com.idm.onepiecelist.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idm.onepiecelist.core.data.source.local.dao.OnePieceDao
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity


@Database(
    entities = [
    OnePieceEntity::class],
    version = 1
)
abstract class OnePieceDatabase : RoomDatabase() {
    abstract fun onePieceDao(): OnePieceDao
}
