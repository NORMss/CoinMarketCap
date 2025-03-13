package ru.normno.coinmarketcap.di

import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.normno.coinmarketcap.core.data.network.HttpClientFactory
import ru.normno.coinmarketcap.crypto.data.networking.RemoteCoinDataSource
import ru.normno.coinmarketcap.crypto.domain.CoinDataSource
import ru.normno.coinmarketcap.crypto.presentation.coin_list.CoinListViewModel

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }

//    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()
    single<CoinDataSource> { RemoteCoinDataSource(get()) }

    viewModel { CoinListViewModel(get()) }
}
