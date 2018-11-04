package ca.nick.boilerplate.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ca.nick.boilerplate.data.Dummy

@Dao
interface DummyDao : BaseDao<Dummy> {

    @Query("SELECT * FROM ${Dummy.TABLE_NAME}")
    fun queryAll(): LiveData<List<Dummy>>

    @Query("DELETE FROM ${Dummy.TABLE_NAME}")
    fun deleteAll()
}