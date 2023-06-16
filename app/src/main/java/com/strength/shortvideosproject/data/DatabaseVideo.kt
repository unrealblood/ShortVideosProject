package com.strength.shortvideosproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("videos_table")
data class DatabaseVideo(
    @PrimaryKey
    val videoUrl : String,
    val videoTitle : String,
    val updated : String,
    val description : String,
    val thumbnail: String
    )

fun List<DatabaseVideo>.asDomianModel() : List<Video> {
    return map {
        Video(
            videoTitle = it.videoTitle,
            videoUrl = it.videoUrl,
            updated = it.updated,
            description = it.description,
            thumbnail = it.thumbnail
        )
    }
}