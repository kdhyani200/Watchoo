package kd.dhyani.project0024.data.remote

import io.reactivex.rxjava3.core.Single
import kd.dhyani.project0024.data.model.MediaItem
import kd.dhyani.project0024.data.model.TitleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchModeApi {

    @GET("list-titles/")
    fun getMovies(
        @Query("apiKey") apiKey: String,
        @Query("types") type: String = "movie"
    ): Single<TitleResponse>

    @GET("list-titles/")
    fun getTvShows(
        @Query("apiKey") apiKey: String,
        @Query("types") type: String = "tv_series"
    ): Single<TitleResponse>

    @GET("title/{id}/details/")
    fun getDetails(
        @Path("id") id: Long,
        @Query("apiKey") apiKey: String
    ): Single<MediaItem>
}
