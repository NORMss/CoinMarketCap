package ru.normno.coinmarketcap.crypto.domain

import ru.normno.coinmarketcap.core.domain.util.NetworkError
import ru.normno.coinmarketcap.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(token: String): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): Result<List<CoinPrice>, NetworkError>
}