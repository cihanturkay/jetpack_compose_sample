package com.sample.got.houseDetail

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.sample.got.DestinationsArgs
import com.sample.got.MainCoroutineRule
import com.sample.got.data.repo.FakeRepository
import com.sample.got.data.repo.FakeRepository.Companion.characterMap
import com.sample.got.data.repo.FakeRepository.Companion.houseMap
import com.sample.got.housedetail.HouseDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HouseDetailViewModelTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var houseDetailViewModel: HouseDetailViewModel

    private lateinit var fakeRepository: FakeRepository
    private val houseId = 1

    @Before
    fun setupViewModel() {
        fakeRepository = FakeRepository()

        houseDetailViewModel = HouseDetailViewModel(
            fakeRepository,
            SavedStateHandle(mapOf(DestinationsArgs.HOUSE_ID_ARG to houseId))
        )
    }

    @Test
    fun getHouseDetailFromRepositoryAndLoadIntoView() = runTest {
        fakeRepository.setReturnError(false)
        houseDetailViewModel.load()
        val uiState = houseDetailViewModel.uiState.first()

        assertThat(uiState.house?.name).isEqualTo(houseMap[houseId]!!.name)
        assertThat(uiState.characters.size).isEqualTo(characterMap.size)
    }

    @Test
    fun getHouseDetailFromRepositoryAndThrowError() = runTest {
        fakeRepository.setReturnError(true)
        houseDetailViewModel.load()
        val uiState = houseDetailViewModel.uiState.first()

        assertThat(uiState.isError).isEqualTo(true)
        assertThat(uiState.characters.size).isEqualTo(0)
    }

}