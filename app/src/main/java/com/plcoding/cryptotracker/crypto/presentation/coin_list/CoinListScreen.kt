package com.plcoding.cryptotracker.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.core.presentation.util.toString
import com.plcoding.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.plcoding.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext

@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
) {
    // 아래 코드는 자주 사용되기 때문에, phillip은 확장 함수로 빼는 것을 선호
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    // lifecycle이 변경되면 다시 실행됨
//    LaunchedEffect(lifecycleOwner.lifecycle) {
//        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//            // immediate를 붙임으로써 context 전환으로 인한
//            // Error 이벤트 처리 누락 방지
//            withContext(Dispatchers.Main.immediate) {
//                events.collect { event ->
//                    // Navigation 이동은 State가 아니다. 이것은 원하는 행위(What)이기 때문에?
//
//                    when(event) {
//                        is CoinListEvent.Error -> {
//                            Toast.makeText(context, event.error.toString(context), Toast.LENGTH_LONG).show()
//                        }
//                    }
//                }
//            }
//        }
//    }

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins, key = { it.id }) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState(
                coins = (1..100).map {
                    previewCoin.copy(id = it.toString())
                }
            ),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}
