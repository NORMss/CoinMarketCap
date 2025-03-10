package ru.normno.coinmarketcap.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import ru.normno.coinmarketcap.crypto.model.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null,
)
