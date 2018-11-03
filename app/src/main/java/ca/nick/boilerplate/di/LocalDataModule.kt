package ca.nick.boilerplate.di

import android.app.Application
import androidx.room.Room
import ca.nick.boilerplate.data.local.Db
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun db(app: Application) =
        Room.databaseBuilder(app, Db::class.java, Db.DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun dao(db: Db) = db.dao()
}