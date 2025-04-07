package com.qinshift.o2.lib.foundation

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class Dimensions(
    val spaceXS: Dp = 8.dp,
    val spaceS: Dp = 12.dp,
    val spaceM: Dp = 16.dp,
    val spaceL: Dp = 20.dp,

    val radiusInput: Dp = 12.dp
) {
    override fun equals(other: Any?): Boolean = other is Dimensions
    override fun hashCode(): Int = 2
    override fun toString(): String = "O2Dimensions"
}

internal val LocalDimensions = staticCompositionLocalOf { Dimensions() }