package com.rijksmuseum.view.designsystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface Spacing {
    val x1: Dp
    val x2: Dp
    val x3: Dp
    val x4: Dp
    val x5: Dp
    val x6: Dp
    val x8: Dp
    val x10: Dp
    val x12: Dp
    val x14: Dp
    val x16: Dp
    val x20: Dp
    val x24: Dp
    val x32: Dp
}

open class DefaultSpacing(
    override val x1: Dp = 4.dp,
    override val x2: Dp = 8.dp,
    override val x3: Dp = 12.dp,
    override val x4: Dp = 16.dp,
    override val x5: Dp = 20.dp,
    override val x6: Dp = 24.dp,
    override val x8: Dp = 32.dp,
    override val x10: Dp = 40.dp,
    override val x12: Dp = 48.dp,
    override val x14: Dp = 56.dp,
    override val x16: Dp = 64.dp,
    override val x20: Dp = 80.dp,
    override val x24: Dp = 96.dp,
    override val x32: Dp = 128.dp
) : Spacing
