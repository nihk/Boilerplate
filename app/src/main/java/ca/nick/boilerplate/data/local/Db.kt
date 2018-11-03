package ca.nick.boilerplate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ca.nick.boilerplate.data.Dummy

@Database(entities = [Dummy::class], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {

    abstract fun dao(): DummyDao

    companion object {
        const val DATABASE_NAME = "todo.db"
    }
}