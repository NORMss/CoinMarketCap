package ru.normno.coinmarketcap.crypto.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.normno.coinmarketcap.crypto.domain.Coin
import ru.normno.coinmarketcap.crypto.model.CoinUi
import ru.normno.coinmarketcap.crypto.model.toCoinUi
import ru.normno.coinmarketcap.crypto.ui.theme.CoinMarketCapTheme

@Composable
fun CoinListItem(
    coin: CoinUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = coin.iconRes),
            contentDescription = coin.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(85.dp),
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = coin.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor,
            )
            Text(
                text = coin.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = contentColor,
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = "$ ${coin.priceUsd.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor,
            )
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            PriceChange(
                change = coin.changePercent24Hr,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListItemPreview() {
    CoinMarketCapTheme {
        CoinListItem(
            coin = previewCoin,
            onClick = {},
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer),
        )
    }
}

internal val previewCoin = Coin(
    id = "id_btc",
    rank = 1,
    name = "Bitcoin",
    symbol = "btc",
    marketCapUsd = 1_651_567_890.0,
    priceUsd = 83_458.83,
    changePercent24Hr = 3.36,
).toCoinUi()