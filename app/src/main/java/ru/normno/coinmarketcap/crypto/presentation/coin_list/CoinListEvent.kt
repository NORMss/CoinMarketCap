package ru.normno.coinmarketcap.crypto.presentation.coin_list

import ru.normno.coinmarketcap.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}