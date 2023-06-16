package com.strength.shortvideosproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.strength.shortvideosproject.data.Video
import com.strength.shortvideosproject.data.VideosDatabase
import com.strength.shortvideosproject.data.asDomianModel
import com.strength.shortvideosproject.network.AppNetworking
import com.strength.shortvideosproject.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val videosDatabase: VideosDatabase) {

    val videos : LiveData<List<Video>> = Transformations.map(videosDatabase.databaseDao.getAllVideos()) {
        it.asDomianModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {

            val playlist = AppNetworking.shortVideos.getPlaylist().await()

            videosDatabase.databaseDao.insertAll(*playlist.asDatabaseModel())
        }
    }

}