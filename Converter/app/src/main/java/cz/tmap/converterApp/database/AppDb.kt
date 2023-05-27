package cz.tmap.converterApp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ConversionDB::class], version = 1)
@TypeConverters(TimestampConverter::class)
abstract class AppDb : RoomDatabase() {
    abstract fun conversionDao(): InterfaceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            var instance = INSTANCE
            if (instance != null) {
                return instance
            }
            instance = Room.databaseBuilder(
                context.applicationContext, AppDb::class.java, "Conversions"
            ).allowMainThreadQueries().build()
            return instance
        }
    }
}