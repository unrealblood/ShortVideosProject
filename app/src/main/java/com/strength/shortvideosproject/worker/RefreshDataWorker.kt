package com.strength.shortvideosproject.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.strength.shortvideosproject.data.VideosDatabase
import com.strength.shortvideosproject.repository.VideosRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    companion object {
        val WORK_NAME = "Refresh Data Worker"
    }

    override suspend fun doWork(): Result {
        val database = VideosDatabase.getDatabase(applicationContext)
        val repository = VideosRepository(database)

        return try {
            repository.refreshVideos()
            Result.success()
        } catch (exception : HttpException) {
            Result.retry()
        }
    }

}