package edu.quinnipiac.ser210.fuelapp2.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.quinnipiac.ser210.fuelapp2.api.Fuel
import kotlinx.coroutines.launch

/**
 * UI state for the Home screen
 */
sealed interface FuelUiState {
    data class Success(val photos: String) : FuelUiState
    object Error : FuelUiState
    object Loading : FuelUiState
}

class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var FuelUiState: String by mutableStateOf<List<Fuel>>(emptyList())
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getFuelPrices()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getFuelPrices() {
        viewModelScope.launch {

            val listResult = FuelApi.retrofitService.getCityFuelPriceToday("maharashtra", "mumbai")

            FuelUiState = listResult

        }
    }
}
