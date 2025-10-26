package kd.dhyani.project0024.data.model

import com.google.gson.annotations.SerializedName

data class MediaItem(
    val id: Long,
    val title: String?,
    val runtime_minutes: Int?,
    @SerializedName("user_rating") val userRating: Double,
    @SerializedName("plot_overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("poster") val posterUrl: String?
)