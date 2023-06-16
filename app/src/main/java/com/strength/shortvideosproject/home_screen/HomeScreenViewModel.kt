package com.strength.shortvideosproject.home_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.strength.shortvideosproject.data.VideosDatabase
import com.strength.shortvideosproject.repository.VideosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val application: Application) : AndroidViewModel(application) {

    //variables
    private val database = VideosDatabase.getDatabase(application.applicationContext)
    private val videosRepository = VideosRepository(database)

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        viewModelScope.launch {
            videosRepository.refreshVideos()
        }
    }

    val playlist = videosRepository.videos

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }
}