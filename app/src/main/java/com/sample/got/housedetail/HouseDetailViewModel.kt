package com.sample.got.housedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.got.DestinationsArgs
import com.sample.got.data.model.Character
import com.sample.got.data.model.House
import com.sample.got.data.model.Result
import com.sample.got.data.repo.Repository
import com.sample.got.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class HouseDetailUIState(
    val isLoading: Boolean,
    val isError: Boolean,
    val house: House? = null,
    val characters: List<Character> = emptyList(),
)

@HiltViewModel
class HouseDetailViewModel @Inject constructor(
    private val Repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val houseId: Int = savedStateHandle[DestinationsArgs.HOUSE_ID_ARG]!!
    private val _isLoading = MutableStateFlow(false)
    private val _isError = MutableStateFlow(false)
    private val _characters = MutableStateFlow(emptyList<Character>())
    private val _house = MutableStateFlow<House?>(null)

    init {
        load()
    }

    val uiState: StateFlow<HouseDetailUIState> = combine(
        _isLoading, _isError, _house, _characters
    ) { isLoading, isError, house, characters ->
        HouseDetailUIState(isLoading = isLoading, isError = isError, house, characters)
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = HouseDetailUIState(isLoading = true, isError = false)
    )

    private suspend fun fillData() = withContext(Dispatchers.IO) {
        _isLoading.value = true
        _isError.value = false
        _house.value = null
        _characters.value = emptyList()
        val houseResult = Repository.getHouse(houseId)
        if (houseResult is Result.Success) {
            _house.value = houseResult.data
            val charactersResult = Repository.getCharacters(houseResult.data.swornMembers.map {
                it.substringAfterLast("/").toInt()
            })

            if (charactersResult is Result.Success) {
                _characters.value = charactersResult.data
            }
        } else {
            _isError.value = true
        }
        _isLoading.value = false
    }

    fun load() {
        viewModelScope.launch {
            fillData()
        }
    }

}
