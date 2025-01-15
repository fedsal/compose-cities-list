# compose-city-list
 This application is build using Clean Architecture and MVVM as presentation pattern.
 
 Ii is also offline-first, the cities are stored in local database and updated asynchronously.

 ## Search 

 The search/filtering is executed with Kotlin flows filtering built-in option

 ``` koltin
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
```

It takes three flows and combines it to create a new one that contains all the cities filtered. It allows you to filter per country code (e.g. AR) and city name.

You can find all the acceptance criteria used to build this app in the following document: 

[Mobile Challenge - v0.5.pdf](https://github.com/user-attachments/files/18423793/Mobile.Challenge.-.v0.5.pdf)

## IMPORTANT
You will need a Google maps API key. Set it in AndroidManifest file.

``` xml
<meta-data
            android:name="com.google.android.maps.v2.API_KEY"
            android:value="API-KEY" />
```
