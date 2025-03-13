package ru.normno.coinmarketcap

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.normno.coinmarketcap.core.presentation.util.ObserveAsEvents
import ru.normno.coinmarketcap.core.presentation.util.toString
import ru.normno.coinmarketcap.crypto.presentation.coin_list.CoinListEvent
import ru.normno.coinmarketcap.crypto.presentation.coin_list.CoinListScreen
import ru.normno.coinmarketcap.crypto.presentation.coin_list.CoinListViewModel
import ru.normno.coinmarketcap.crypto.ui.theme.CoinMarketCapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinMarketCapTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current
                    ObserveAsEvents(
                        events = viewModel.events,
                    ) { event ->
                        when (event) {
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    CoinListScreen(
                        state = state,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}