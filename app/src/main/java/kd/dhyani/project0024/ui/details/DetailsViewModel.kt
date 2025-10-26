package kd.dhyani.project0024.ui.details

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kd.dhyani.project0024.data.model.MediaItem
import kd.dhyani.project0024.repository.WatchModeRepository
import retrofit2.HttpException

class DetailsViewModel(private val repo: WatchModeRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    data class UiState(
        val isLoading: Boolean = false,
        val item: MediaItem? = null,
        val errorMessage: String? = null
    )

    private val _uiState = mutableStateOf(UiState())
    val uiState: State<UiState> = _uiState

    fun loadDetails(id: Long) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        val disposable = repo.getDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                _uiState.value = _uiState.value.copy(isLoading = false, item = item)
            }, { err ->
                val message = if (err is HttpException && err.code() == 401)
                    "Unauthorized: Check API Key"
                else
                    err.localizedMessage ?: "Unknown error"
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = message)
            })
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
