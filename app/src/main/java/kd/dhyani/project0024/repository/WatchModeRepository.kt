package kd.dhyani.project0024.repository

import io.reactivex.rxjava3.core.Single
import kd.dhyani.project0024.data.model.MediaItem
import kd.dhyani.project0024.data.model.TitleResponse
import kd.dhyani.project0024.data.remote.WatchModeApi

class WatchModeRepository(private val api: WatchModeApi) {

    private val apiKey = "gi1x1aDgv1KMfamWNNxddiE2RuIGZ95DF86QfAcO"

    fun getMovies(): Single<TitleResponse> =
        api.getMovies(apiKey)

    fun getTvShows(): Single<TitleResponse> =
        api.getTvShows(apiKey)

    // 🔹 Perform both calls simultaneously
    fun getMoviesAndTvShows(): Single<Pair<TitleResponse, TitleResponse>> {
        return Single.zip(
            getMovies(),
            getTvShows(),
            { movies, tvShows -> Pair(movies, tvShows) }
        )
    }

    fun getDetails(id: Long): Single<MediaItem> =
        api.getDetails(id, apiKey)
}
