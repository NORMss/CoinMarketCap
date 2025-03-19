package ru.normno.coinmarketcap.crypto.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import ru.normno.coinmarketcap.core.data.network.constructUrl
import ru.normno.coinmarketcap.core.data.network.safeCall
import ru.normno.coinmarketcap.core.domain.util.NetworkError
import ru.normno.coinmarketcap.core.domain.util.Result
import ru.normno.coinmarketcap.core.domain.util.map
import ru.normno.coinmarketcap.crypto.data.mapper.toCoin
import ru.normno.coinmarketcap.crypto.data.mapper.toCoinPrice
import ru.normno.coinmarketcap.crypto.data.networking.dto.CoinHistoryDto
import ru.normno.coinmarketcap.crypto.data.networking.dto.CoinResponseDto
import ru.normno.coinmarketcap.crypto.domain.Coin
import ru.normno.coinmarketcap.crypto.domain.CoinDataSource
import ru.normno.coinmarketcap.crypto.domain.CoinPrice
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient,
) : CoinDataSource {
    override suspend fun getCoins(
        token: String,
    ): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            ) {
                parameter("apiKey", token)
            }
        }.map { response ->
            response.coins.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { responce ->
            responce.data.map { it.toCoinPrice() }
        }
    }
}