package ru.normno.coinmarketcap.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.normno.coinmarketcap.core.domain.util.onError
import ru.normno.coinmarketcap.core.domain.util.onSuccess
import ru.normno.coinmarketcap.crypto.domain.CoinDataSource
import ru.normno.coinmarketcap.crypto.model.toCoinUi

class CoinListViewModel(
    private val coinDataSource: CoinDataSource,
) : ViewModel() {
    val state: StateFlow<CoinListState>
        field = MutableStateFlow(CoinListState())

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    init {
        loadCoins()
        state
            .onStart {
                loadCoins()
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000L),
                CoinListState()
            )
    }

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnClickCoin -> {
                state.update {
                    it.copy(
                        selectedCoin = action.coinUI,
                    )
                }
            }

            CoinListAction.OnRefresh -> {
                loadCoins()
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = true
                )
            }
            coinDataSource
                .getCoins(
                    token = "7897b43b1983aecd76db597cdc5d428e51b430200a83761716aee595423457bf"
                )
                .onSuccess { coins ->
                    state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { it.toCoinUi() },
                        )
                    }
                }
                .onError { error ->
                    state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    _events.send(CoinListEvent.Error(error = error))
                }
        }
    }
}