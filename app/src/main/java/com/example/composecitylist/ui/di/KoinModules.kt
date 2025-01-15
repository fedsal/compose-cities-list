package com.example.composecitylist.ui.di

import com.example.composecitylist.data.CityRepository
import com.example.composecitylist.framework.retrofit.RemoteCityDataSource
import com.example.composecitylist.framework.room.AppDatabase
import com.example.composecitylist.framework.room.LocalCityDataSource
import com.example.composecitylist.ui.screens.cities.CityViewmodel
import com.example.composecitylist.ui.utils.DefaultDispatcherProvider
import com.example.composecitylist.ui.utils.DispatcherProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidContext()).cityDao() }
    single { CityRepository(RemoteCityDataSource(), LocalCityDataSource(get())) }
    single<DispatcherProvider> { DefaultDispatcherProvider() }
    viewModel { CityViewmodel(get(), get()) }
}