package com.sample.got.houses

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.got.data.model.House
import com.sample.got.data.model.Result
import com.sample.got.data.repo.GOTRepository
import com.sample.got.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class HousesUiState(
    val items: List<House> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)

@HiltViewModel
class HousesViewModel @Inject constructor(
    private val GOTRepository: GOTRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _isError = MutableStateFlow(false)
    private val _houses: Flow<Result<List<House>>> = GOTRepository.getHouses()


    val uiState: StateFlow<HousesUiState> =
        combine(_isLoading, _isError, _houses) { isLoading, isError, houses ->
            when (houses) {
                is Result.Success -> {
                    HousesUiState(
                        items = houses.data,
                        isLoading = isLoading,
                        isError = isError,
                    )
                }
                is Result.Error -> {
                    HousesUiState(isError = true)
                }
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = WhileUiSubscribed,
                initialValue = HousesUiState(isLoading = true, isError = false)
            )

}