package com.plcoding.cryptotracker.core.presentation.util

import android.content.Context
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when(this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_request
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_unknown // 보안 상의 이유로 사용자에게 서버 오류라는 것을 보여주지 않는 것이 좋다.
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
    }
    return context.getString(resId)
}
