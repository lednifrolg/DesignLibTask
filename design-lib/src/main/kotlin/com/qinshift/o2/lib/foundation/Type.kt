package com.qinshift.o2.lib.foundation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.lerp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.qinshift.o2.lib.R

@Stable
class Typography internal constructor(
    labelM: TextStyle,
    labelS: TextStyle,
    bodyM: TextStyle,
) {
    var labelM by mutableStateOf(labelM, structuralEqualityPolicy())
        internal set
    var labelS by mutableStateOf(labelS, structuralEqualityPolicy())
        internal set
    var bodyM by mutableStateOf(bodyM, structuralEqualityPolicy())

    internal fun copy(
        labelM: TextStyle = this.labelM,
        labelS: TextStyle = this.labelS,
        bodyM: TextStyle = this.bodyM,
    ) = Typography(
        labelM = labelM,
        labelS = labelS,
        bodyM = bodyM,
    )

    override fun toString(): String = "Typography"
}

internal fun initialTypography() = Typography(
    labelM = TextStyle(
        fontFamily = O2FontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 22.sp,
        letterSpacing = 0.01.em
    ),
    labelS = TextStyle(
        fontFamily = O2FontFamily,
        fontSize = 14.sp,
        fontWeight = lerp(FontWeight.W500, FontWeight.W600, 0.5f),
        lineHeight = 17.sp,
        letterSpacing = 0.011.em
    ),
    bodyM = TextStyle(
        fontFamily = O2FontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 22.sp,
        letterSpacing = 0.0006.em,
    )
)

internal fun colorAwareTypography(
    textPrimary: Color,
) = with(initialTypography()) {
    copy(
        labelM = labelM.copy(color = textPrimary),
        labelS = labelS.copy(color = textPrimary),
        bodyM = bodyM.copy(color = textPrimary),
    )
}

internal val LocalTypography = staticCompositionLocalOf { initialTypography() }

internal val O2FontFamily: FontFamily = FontFamily(
    Font(R.font.inter_400, FontWeight.W400, FontStyle.Normal),
    Font(R.font.inter_500, FontWeight.W500, FontStyle.Normal),
    Font(R.font.inter_600, FontWeight.W600, FontStyle.Normal),
)