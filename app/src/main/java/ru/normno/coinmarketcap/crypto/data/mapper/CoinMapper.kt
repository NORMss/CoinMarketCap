package ru.normno.coinmarketcap.crypto.data.mapper

import ru.normno.coinmarketcap.crypto.data.networking.dto.CoinDto
import ru.normno.coinmarketcap.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
    )
}