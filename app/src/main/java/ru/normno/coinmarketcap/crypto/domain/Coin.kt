package ru.normno.coinmarketcap.crypto.domain

import kotlinx.serialization.Serializable

@Serializable
data class Coin(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double? = null,
)
