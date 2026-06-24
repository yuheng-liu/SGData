package com.liuyuheng.sgdata.core.data.models

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    sealed class Error : ApiResult<Nothing>() {
        data class NetworkError(val exception: Throwable, val message: String) : Error()
        data class HttpError(val exception: Throwable, val message: String) : Error()
        data class UnknownError(val exception: Throwable, val message: String) : Error()
    }
}