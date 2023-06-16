package com.strength.shortvideosproject.network

import com.squareup.moshi.JsonClass
import com.strength.shortvideosproject.data.DatabaseVideo
import com.strength.shortvideosproject.data.Video

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val videoTitle: String,
    val description: String,
    val videoUrl: String,
    val updated: String,
    val thumbnail: String,
)

fun NetworkVideoContainer.asDomainModel(): List<Video> {
    return videos.map {
        Video(
            videoTitle = it.videoTitle,
            description = it.description,
            videoUrl = it.videoUrl,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}

fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideo> {
    return videos.map {
        DatabaseVideo(
            videoTitle = it.videoTitle,
            description = it.description,
            videoUrl = it.videoUrl,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }.toTypedArray()
}