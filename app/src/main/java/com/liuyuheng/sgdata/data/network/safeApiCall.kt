package com.liuyuheng.sgdata.data.network

import android.util.Log
import com.liuyuheng.sgdata.data.network.response.ErrorResponse
import com.liuyuheng.sgdata.domain.ApiResult
import com.liuyuheng.sgdata.utils.Constants
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T,
): ApiResult<T> = withContext(Dispatchers.IO) {
    try {
        val result = apiCall()
        ApiResult.Success(result)
    } catch (e: HttpException) {
        Log.e("safeApiCall", e.toString())
        e.response()?.errorBody()?.string()?.let { errorBody ->
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            val errorResponse = moshiAdapter.fromJson(errorBody)

            ApiResult.Error(
                exception = e,
                message = errorResponse?.errorMsg ?: Constants.UNKNOWN_ERROR,
            )
        } ?: ApiResult.Error(
            exception = e,
            message = when (e.code()) {
                401 -> Constants.UNAUTHORIZED
                404 -> Constants.NOT_FOUND
                500 -> Constants.SERVER_ERROR
                503 -> Constants.SERVICE_UNAVAILABLE
                else -> Constants.UNKNOWN_ERROR + ": " + e.message()
            }
        )
    } catch (e: IOException) {
        Log.e("safeApiCall", e.toString())
        ApiResult.Error(e, Constants.CONNECTION_ISSUE)
    } catch (e: Exception) {
        Log.e("safeApiCall", e.toString())
        ApiResult.Error(e, Constants.UNKNOWN_ERROR)
    }
}