package com.liuyuheng.sgdata.core.domain.models

sealed class DomainResult<out T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    sealed class Failure : DomainResult<Nothing>() {
        data class NoData(val message: String) : Failure()
        data class Unavailable(val message: String) : Failure()
    }
}