package ru.normno.coinmarketcap.crypto.presentation.coin_list

import ru.normno.coinmarketcap.crypto.model.CoinUi

sealed interface CoinListAction {
    data class OnClickCoin(val coinUI: CoinUi) : CoinListAction
    data object OnRefresh : CoinListAction
}