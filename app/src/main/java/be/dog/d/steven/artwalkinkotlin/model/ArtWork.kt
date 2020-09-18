package be.dog.d.steven.artwalkinkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.dog.d.steven.artwalkinkotlin.model.ArtWork.Companion.TABLE_NAME

@Entity (tableName = TABLE_NAME)
data class ArtWork(

    @PrimaryKey
    var id: Int? = 0,
    var year: Int? = 0,
    var characters: String? = null,
    var authors: String? = null,
    var photoUrl: String? = null,
    var type: String? = null,
    var geoLat: Double? = 0.0,
    var geoLong: Double? = 0.0
) {
    companion object {
        const val TABLE_NAME = "art_works"
    }
}