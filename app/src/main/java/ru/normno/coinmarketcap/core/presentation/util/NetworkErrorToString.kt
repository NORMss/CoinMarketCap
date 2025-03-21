package ru.normno.coinmarketcap.core.presentation.util

import android.content.Context
import ru.normno.coinmarketcap.R
import ru.normno.coinmarketcap.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.TOKEN_ERROR -> R.string.token_error
        NetworkError.REQUEST_TIMEOUT -> R.string.request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.too_many_requests
        NetworkError.NO_INTERNET_CONNECTION -> R.string.no_internet_connection
        NetworkError.SERVER_ERROR -> R.string.server_error
        NetworkError.SERIALIZATION_ERROR -> R.string.serialization_error
        NetworkError.UNKNOWN_ERROR -> R.string.unknown_error
    }
    return context.getString(resId)
}