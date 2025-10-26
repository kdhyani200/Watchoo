package kd.dhyani.project0024.di

import kd.dhyani.project0023.ui.home.HomeViewModel
import kd.dhyani.project0024.data.remote.WatchModeApi
import kd.dhyani.project0024.repository.WatchModeRepository
import kd.dhyani.project0024.ui.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

fun createWatchModeApi(): WatchModeApi {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.watchmode.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    return retrofit.create(WatchModeApi::class.java)
}

val appModule = module {
    single { createWatchModeApi() }
    single { WatchModeRepository(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}
