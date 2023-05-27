package cz.tmap.converterApp.database

import androidx.room.*

@Dao
interface InterfaceDao {
    @Query("SELECT * FROM conversions ORDER BY timestamp DESC")
    fun getAll(): List<ConversionDB>

    @Query("DELETE FROM conversions WHERE id = :idValue")
    fun deleteById(idValue: Long)

    @Insert
    fun insert(conversionDB: ConversionDB): Long
}