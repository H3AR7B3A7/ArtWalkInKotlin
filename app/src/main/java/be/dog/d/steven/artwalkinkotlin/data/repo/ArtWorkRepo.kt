package be.dog.d.steven.artwalkinkotlin.data.repo

import androidx.annotation.MainThread
import be.dog.d.steven.artwalkinkotlin.data.local.dao.ArtWorkDAO
import be.dog.d.steven.artwalkinkotlin.data.remote.api.ArtWalkService
import be.dog.d.steven.artwalkinkotlin.model.ArtWork
import be.dog.d.steven.artwalkinkotlin.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class ArtWorkRepo @Inject constructor(
    private val artWorkDao: ArtWorkDAO,
    private val artWalkService: ArtWalkService
) {
    fun getAllArtWorks(): Flow<State<List<ArtWork>>> {
        return object : NetworkBoundRepo<List<ArtWork>, List<ArtWork>>() {
            override suspend fun saveRemoteData(response: List<ArtWork>) =
                artWorkDao.insertArtWork(response)

            override fun fetchFromLocal(): Flow<List<ArtWork>> = artWorkDao.getAllArtWorks()

            override suspend fun fetchFromRemote(): Response<List<ArtWork>> = artWalkService.getArtWorks()

        }.asFlow()
    }

    @MainThread
    fun getArtWorkById(artWorkId: Int): Flow<ArtWork> = artWorkDao.getArtWorkById(artWorkId)
}