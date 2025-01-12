package com.mdanielelel.danielfrontend.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder

@Composable
fun SvgIconComposable(resourceId: Int, size: Int = 24, color: Color = Color.Unspecified) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data("android.resource://${context.packageName}/raw/$resourceId")
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        modifier = Modifier.size(size.dp),
        colorFilter = color.let { ColorFilter.tint(it) }
    )
}