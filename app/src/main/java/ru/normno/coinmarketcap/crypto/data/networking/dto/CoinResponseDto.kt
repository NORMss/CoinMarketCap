package ru.normno.coinmarketcap.crypto.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinResponseDto(
    @SerialName("data")
    val coins: List<CoinDto>
)
