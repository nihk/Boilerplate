package ca.nick.boilerplate.di.module

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import ca.nick.boilerplate.data.local.Db
import ca.nick.boilerplate.di.annotation.StaleDataThreshold
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun db(application: Application) =
        Room.databaseBuilder(application, Db::class.java, Db.DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun dao(db: Db) = db.dao()

    @Singleton
    @Provides
    fun sharedPreferences(application: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application)

    @Singleton
    @Provides
    @StaleDataThreshold
    fun staleDataThreshold() = TimeUnit.MINUTES.toMillis(30)
}