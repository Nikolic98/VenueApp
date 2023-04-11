package com.example.venueapp.viewModel.result

/**
 * @author Marko Nikolic on 11.4.23.
 */
open class ResultState

@Suppress("UNCHECKED_CAST")
fun <T> ResultState.getResultDataIfSuccess(): T? {
    return if (this is SuccessResultState<*>) {
        (this as SuccessResultState<T>).result
    } else {
        null
    }
}

fun ResultState.isError() = this is ErrorResultState

fun ResultState.isSuccess() = this is SuccessResultState<*>

@Suppress("UNCHECKED_CAST")
suspend fun <T> ResultState.onSuccess(block: suspend (T) -> Unit): ResultState {
    if (this is SuccessResultState<*>) {
        block((this as SuccessResultState<T>).result)
    }
    return this
}

suspend fun ResultState.onError(block: suspend (String) -> Unit): ResultState {
    if (this is ErrorResultState) {
        block(error)
    }
    return this
}