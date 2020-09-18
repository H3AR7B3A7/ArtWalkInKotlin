package be.dog.d.steven.artwalkinkotlin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.dog.d.steven.artwalkinkotlin.model.ArtWork
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtWorkDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtWork(ArtWorks: List<ArtWork>)

    @Query("DELETE FROM ${ArtWork.TABLE_NAME}")
    suspend fun deleteAllArtWorks()

    @Query("SELECT * FROM ${ArtWork.TABLE_NAME}")
    fun getAllArtWorks(): Flow<List<ArtWork>>

    @Query("SELECT * FROM ${ArtWork.TABLE_NAME} WHERE ID = :artWorkId")
    fun getArtWorkById(artWorkId: Int): Flow<ArtWork>

    @Query("SELECT * FROM ${ArtWork.TABLE_NAME} WHERE TYPE = :artWorkType")
    fun getArtWorkByType(artWorkType: String): Flow<List<ArtWork>>
}