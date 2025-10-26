package kd.dhyani.project0023.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kd.dhyani.project0024.data.model.MediaItem
import kd.dhyani.project0024.repository.WatchModeRepository

data class HomeUiState(
    val isLoading: Boolean = true,
    val showMovies: Boolean = true,
    val movies: List<MediaItem> = emptyList(),
    val tvShows: List<MediaItem> = emptyList(),
    val items: List<MediaItem> = emptyList(),
    val errorMessage: String? = null
)

class HomeViewModel(private val repo: WatchModeRepository) : ViewModel() {

    private val disposables = CompositeDisposable()
    var uiState = mutableStateOf(HomeUiState())
        private set

    init {
        fetchData()
    }

    private fun fetchData() {
        uiState.value = uiState.value.copy(isLoading = true)

        disposables.add(
            repo.getMoviesAndTvShows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (moviesResponse, tvShowsResponse) ->
                    val movies = moviesResponse.titles ?: emptyList()
                    val tvShows = tvShowsResponse.titles ?: emptyList()

                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        movies = movies,
                        tvShows = tvShows,
                        items = movies, // default tab
                        showMovies = true
                    )
                }, { error ->
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                })
        )
    }

    fun showMovies() {
        uiState.value = uiState.value.copy(
            showMovies = true,
            items = uiState.value.movies
        )
    }

    fun showTvShows() {
        uiState.value = uiState.value.copy(
            showMovies = false,
            items = uiState.value.tvShows
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun retry() {
        fetchData()
    }
}
