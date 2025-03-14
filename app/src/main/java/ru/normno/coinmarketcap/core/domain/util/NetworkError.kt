package ru.normno.coinmarketcap.core.domain.util

enum class NetworkError : Error {
    TOKEN_ERROR,
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET_CONNECTION,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN_ERROR,
}