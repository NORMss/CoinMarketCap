package ru.normno.coinmarketcap.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.normno.coinmarketcap.crypto.domain.Coin
import ru.normno.coinmarketcap.crypto.model.toCoinUi
import ru.normno.coinmarketcap.crypto.presentation.coin_list.components.CoinListItem
import ru.normno.coinmarketcap.crypto.ui.theme.CoinMarketCapTheme

@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
) {

    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = state.coins,
            ) { coinUi ->
                CoinListItem(
                    coin = coinUi,
                    onClick = {

                    },
                )
                HorizontalDivider()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    CoinMarketCapTheme {
        CoinListScreen(
            state = CoinListState(
                isLoading = false,
                coins = coins,
            ),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer),
        )
    }
}

internal val coins = listOf(
    Coin(
        id = "id_btc",
        rank = 1,
        name = "Bitcoin",
        symbol = "btc",
        marketCapUsd = 1_651_567_890.0,
        priceUsd = 83_458.83,
        changePercent24Hr = 3.36,
    ),
    Coin(
        id = "id_eth",
        rank = 2,
        name = "Ethereum",
        symbol = "eth",
        marketCapUsd = 754_123_456.0,
        priceUsd = 4_578.12,
        changePercent24Hr = 2.14,
    ),
    Coin(
        id = "id_usdt",
        rank = 3,
        name = "Tether",
        symbol = "usdt",
        marketCapUsd = 932_678_910.0,
        priceUsd = 1.00,
        changePercent24Hr = 0.02,
    ),
    Coin(
        id = "id_bnb",
        rank = 4,
        name = "Binance Coin",
        symbol = "bnb",
        marketCapUsd = 102_345_678.0,
        priceUsd = 678.45,
        changePercent24Hr = -1.23,
    ),
    Coin(
        id = "id_xrp",
        rank = 5,
        name = "XRP",
        symbol = "xrp",
        marketCapUsd = 67_890_123.0,
        priceUsd = 0.89,
        changePercent24Hr = 0.56,
    ),
    Coin(
        id = "id_sol",
        rank = 6,
        name = "Solana",
        symbol = "sol",
        marketCapUsd = 85_432_567.0,
        priceUsd = 187.23,
        changePercent24Hr = 4.78,
    ),
    Coin(
        id = "id_ada",
        rank = 7,
        name = "Cardano",
        symbol = "ada",
        marketCapUsd = 45_678_234.0,
        priceUsd = 1.23,
        changePercent24Hr = -0.89,
    ),
    Coin(
        id = "id_doge",
        rank = 8,
        name = "Dogecoin",
        symbol = "doge",
        marketCapUsd = 39_456_789.0,
        priceUsd = 0.15,
        changePercent24Hr = 1.67,
    ),
).map {
    it.toCoinUi()
}