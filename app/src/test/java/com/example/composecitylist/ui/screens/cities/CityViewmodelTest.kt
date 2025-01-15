package com.example.composecitylist.ui.screens.cities

import com.example.composecitylist.data.CityRepository
import com.example.composecitylist.domain.City
import com.example.composecitylist.ui.utils.TestDispatcherProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CityViewmodelTest {

    private lateinit var cityViewmodel: CityViewmodel
    @MockK
    private lateinit var cityRepository: CityRepository

    private lateinit var testDispatcherProvider: TestDispatcherProvider

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testDispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun `filtering per letter should return all cities that has that letter`() = runTest {
        // Given
        val cities = listOf(
            City(1, "London", "UK", 1.0,1.0),
            City(2, "New York", "US", 1.0,1.0),
            City(3, "Paris", "FR", 1.0,1.0),
            City(4, "Berlin", "DE", 1.0,1.0),
            City(5, "Tokyo", "JP", 1.0,1.0),
        )
        coEvery { cityRepository.getCities() } returns flow { emit(cities) }
        cityViewmodel = CityViewmodel(cityRepository, testDispatcherProvider)
        advanceUntilIdle()
        // When
        cityViewmodel.filterCities("o")
        // Then
        assertEquals(3, cityViewmodel.uiState.value.cities.size)
    }

    @Test
    fun `filtering per query should return all cities that contains it`() = runTest {
        // Given
        val cities = listOf(
            City(1, "London", "UK", 1.0,1.0),
            City(2, "New York", "US", 1.0,1.0),
            City(3, "Paris", "FR", 1.0,1.0),
            City(4, "Berlin", "DE", 1.0,1.0),
            City(5, "Tokyo", "JP", 1.0,1.0),
        )
        coEvery { cityRepository.getCities() } returns flow { emit(cities) }
        cityViewmodel = CityViewmodel(cityRepository, testDispatcherProvider)
        advanceUntilIdle()
        // When
        cityViewmodel.filterCities("don")
        advanceUntilIdle()
        // Then
        assertEquals(1, cityViewmodel.uiState.value.cities.size)
    }

    @Test
    fun `filtering per query should return all cities that contains it and is case insensitive`() = runTest {
        // Given
        val cities = listOf(
            City(1, "London", "UK", 1.0,1.0),
            City(2, "New York", "US", 1.0,1.0),
            City(3, "Paris", "FR", 1.0,1.0),
            City(4, "Berlin", "DE", 1.0,1.0),
            City(5, "Tokyo", "JP", 1.0,1.0),
        )
        coEvery { cityRepository.getCities() } returns flow { emit(cities) }
        cityViewmodel = CityViewmodel(cityRepository, testDispatcherProvider)
        advanceUntilIdle()
        // When
        cityViewmodel.filterCities("LoN")
        advanceUntilIdle()
        // Then
        assertEquals(0, cityViewmodel.uiState.value.cities.size)
    }

    @Test
    fun `filtering per country code should return all cities of that country`() = runTest {
        // Given
        val cities = listOf(
            City(1, "London", "UK", 1.0,1.0),
            City(2, "New York", "US", 1.0,1.0),
            City(2, "Washington DC", "US", 1.0,1.0),
            City(3, "Paris", "FR", 1.0,1.0),
            City(4, "Berlin", "DE", 1.0,1.0),
            City(5, "Tokyo", "JP", 1.0,1.0),
        )
        coEvery { cityRepository.getCities() } returns flow { emit(cities) }
        cityViewmodel = CityViewmodel(cityRepository, testDispatcherProvider)
        advanceUntilIdle()
        // When
        cityViewmodel.filterCities("US")
        advanceUntilIdle()
        // Then
        assertEquals(2, cityViewmodel.uiState.value.cities.size)
    }

    @Test
    fun `filtering per favourite should return all favourites cities`() = runTest {
        // Given
        val cities = listOf(
            City(1, "London", "UK", 1.0,1.0),
            City(2, "New York", "US", 1.0,1.0),
            City(2, "Washington DC", "US", 1.0,1.0),
            City(3, "Paris", "FR", 1.0,1.0, true),
            City(4, "Berlin", "DE", 1.0,1.0),
            City(5, "Tokyo", "JP", 1.0,1.0, true),
        )
        coEvery { cityRepository.getCities() } returns flow { emit(cities) }
        cityViewmodel = CityViewmodel(cityRepository, testDispatcherProvider)
        advanceUntilIdle()
        // When
        cityViewmodel.filterFavourites(true)
        advanceUntilIdle()
        // Then
        assertEquals(2, cityViewmodel.uiState.value.cities.size)
    }


}