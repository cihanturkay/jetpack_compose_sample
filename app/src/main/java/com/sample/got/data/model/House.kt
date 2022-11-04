package com.sample.got.data.model

data class House(
    val url: String = "",
    val name: String = "",
    val region: String = "",
    val coatOfArms: String = "",
    val words: String = "",
    val titles: ArrayList<String> = arrayListOf(),
    val seats: ArrayList<String> = arrayListOf(),
    val currentLord: String = "",
    val heir: String = "",
    val overlord: String = "",
    val founded: String = "",
    val founder: String = "",
    val diedOut: String = "",
    val ancestralWeapons: ArrayList<String> = arrayListOf(),
    val cadetBranches: ArrayList<String> = arrayListOf(),
    val swornMembers: ArrayList<String> = arrayListOf()
)