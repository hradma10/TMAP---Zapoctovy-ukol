package cz.tmap.converterApp.database

import androidx.room.*
import java.sql.Timestamp

@Entity(tableName = "conversions")
data class ConversionDB(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,
    val fromLabel: String,
    val fromNumber: String,
    val fromUnit: String,
    val toLabel: String,
    val toNumber: String,
    val toUnit: String,
    val timestamp: Timestamp
)




