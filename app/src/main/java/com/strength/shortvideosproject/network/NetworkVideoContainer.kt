package com.strength.shortvideosproject.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos : List<NetworkVideo>)