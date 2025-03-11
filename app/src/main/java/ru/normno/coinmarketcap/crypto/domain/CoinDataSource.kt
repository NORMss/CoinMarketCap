package ru.normno.coinmarketcap.crypto.domain

import ru.normno.coinmarketcap.core.domain.util.NetworkError
import ru.normno.coinmarketcap.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}