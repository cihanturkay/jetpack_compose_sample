package com.sample.got.data.repo

import com.sample.got.data.model.Character
import com.sample.got.data.model.House
import com.sample.got.data.model.Result


class FakeRepository : Repository {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getHouses(page: Int, pageSize: Int): Result<List<House>> {
        return if (shouldReturnError) {
            Result.Error(Exception("Error"))
        } else {
            Result.Success(houseMap.values.toList())
        }
    }

    override suspend fun getHouse(id: Int): Result<House> {
        return if (shouldReturnError) {
            Result.Error(Exception("Error"))
        } else {
            Result.Success(houseMap[id]!!)
        }
    }

    override suspend fun getCharacters(characterIds: List<Int>): Result<List<Character>> {
        return if (shouldReturnError) {
            Result.Error(Exception("Error"))
        } else {
            Result.Success(characterMap.values.toList())
        }
    }

    companion object {
        val sampleCharacter = Character(
            url = "https://anapioficeandfire.com/api/characters/1",
            name = "Jon Snow",
            gender = "Male",
            culture = "Northmen",
            born = "In 283 AC",
            titles = listOf(),
            aliases = listOf(),
            allegiances = listOf(),
            books = listOf(),
            died = "",
            father = "",
            mother = "",
            playedBy = listOf(),
            povBooks = listOf(),
            spouse = "",
            tvSeries = listOf()
        )
        val sampleHouse = House(
            url = "https://anapioficeandfire.com/api/houses/1",
            coatOfArms = "",
            titles = listOf(),
            seats = listOf(),
            currentLord = "",
            heir = "",
            overlord = "",
            founded = "",
            founder = "",
            diedOut = "",
            ancestralWeapons = listOf(),
            cadetBranches = listOf(),
            name = "House Targaryen of King's Landing",
            region = "The Crownlands",
            words = "Fire and Blood",
            swornMembers = arrayListOf(
                "https://anapioficeandfire.com/api/characters/1",
            )
        )
        val houseMap = mapOf(
            1 to sampleHouse,
            2 to sampleHouse.copy(url = "/2"),
            3 to sampleHouse.copy(url = "/3")
        )

        val characterMap = mapOf(
            1 to sampleCharacter,
            2 to sampleCharacter.copy(url = "/2"),
            3 to sampleCharacter.copy(url = "/3")
        )
    }

}
