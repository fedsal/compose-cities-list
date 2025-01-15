package com.example.composecitylist.ui.screens.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecitylist.data.CityRepository
import com.example.composecitylist.domain.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CityViewmodel(
    private val cityRepository: CityRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(CityUIState())
    val uiState: StateFlow<CityUIState> get() = _uiState
    private val isFavouriteFilterActive = MutableStateFlow(false)
    private val searchQuery = MutableStateFlow("")

    init {
        getCities()
    }

    private fun getCities() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        cityRepository.getCities()
            .distinctUntilChanged()
            .combine(isFavouriteFilterActive) { cities, isFavouriteFilterActive ->
                if (isFavouriteFilterActive) {
                    cities.filter { it.isFavourite }
                } else {
                    cities
                }
            }
            .combine(searchQuery) { cities, query ->
                if (query.isEmpty()) {
                    cities
                } else {
                    cities.filter { it.name.contains(query) || it.countryCode.contains(query) }
                }
            }
            .collect { filteredCities ->
                _uiState.value = _uiState.value.copy(cities = filteredCities, isLoading = false)
            }
    }

    fun updateFavouriteCity(city: City) = viewModelScope.launch(Dispatchers.IO) {
        cityRepository.updateFavouriteCity(city.id, !city.isFavourite)
    }

    fun filterFavourites(showFilter: Boolean) {
        isFavouriteFilterActive.update { showFilter }
    }

    fun filterCities(query: String) {
        searchQuery.update { query }
    }
}
