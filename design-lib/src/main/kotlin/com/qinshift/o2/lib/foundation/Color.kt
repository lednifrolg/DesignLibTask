package com.qinshift.o2.lib.foundation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

internal val CoreGray950 = Color(0xFF2C2C31)
internal val CoreGray500 = Color(0xFF8C8C9A)
internal val CoreGray300 = Color(0xFFC9C9CE)
internal val CoreGray00 = Color(0xFFFFFFFF)

internal val O2Blue500 = Color(0xFF0050FF)

internal val CoreRed700 = Color(0xFFA56315)
internal val CoreRed600 = Color(0xFFDC2828)
internal val CoreRed100 = Color(0xFFFFDCDC)

internal val CoreYellow700 = Color(0xFFA56315)
internal val CoreYellow100 = Color(0xFFFAF1B6)

internal val CoreAlphaDim50 = Color(0x0F1A1A1A)
internal val CoreAlphaDim800 = Color(0xCC1A1A1A)

@Stable
class Colors(
    surfaceXHigh: Color,
    surfaceXLow: Color,
    surfaceBrand: Color,
    surfaceDanger: Color,
    surfaceDangerVariant: Color,
    surfaceWarning: Color,
    surfaceWarningVariant: Color,
    contentOnNeutralXxHigh: Color,
    contentOnNeutralMedium: Color,
    contentOnNeutralLow: Color,
    contentOnNeutralDanger: Color,
    contentOnNeutralWarning: Color,
    stateDefaultHover: Color,
    stateDefaultFocus: Color
) {
    var surfaceXHigh by mutableStateOf(surfaceXHigh, structuralEqualityPolicy())
        internal set
    var surfaceXLow by mutableStateOf(surfaceXLow, structuralEqualityPolicy())
        internal set
    var surfaceBrand by mutableStateOf(surfaceBrand, structuralEqualityPolicy())
        internal set
    var surfaceDanger by mutableStateOf(surfaceDanger, structuralEqualityPolicy())
        internal set
    var surfaceDangerVariant by mutableStateOf(surfaceDangerVariant, structuralEqualityPolicy())
        internal set
    var surfaceWarning by mutableStateOf(surfaceWarning, structuralEqualityPolicy())
        internal set
    var surfaceWarningVariant by mutableStateOf(surfaceWarningVariant, structuralEqualityPolicy())
        internal set
    var contentOnNeutralXxHigh by mutableStateOf(contentOnNeutralXxHigh, structuralEqualityPolicy())
        internal set
    var contentOnNeutralMedium by mutableStateOf(contentOnNeutralMedium, structuralEqualityPolicy())
        internal set
    var contentOnNeutralLow by mutableStateOf(contentOnNeutralLow, structuralEqualityPolicy())
        internal set
    var contentOnNeutralDanger by mutableStateOf(contentOnNeutralDanger, structuralEqualityPolicy())
        internal set
    var contentOnNeutralWarning by mutableStateOf(
        contentOnNeutralWarning,
        structuralEqualityPolicy()
    )
        internal set
    var stateDefaultHover by mutableStateOf(stateDefaultHover, structuralEqualityPolicy())
        internal set
    var stateDefaultFocus by mutableStateOf(stateDefaultFocus, structuralEqualityPolicy())
        internal set

    internal fun copy(
        surfaceXHigh: Color = this.surfaceXHigh,
        surfaceXLow: Color = this.surfaceXLow,
        surfaceBrand: Color = this.surfaceBrand,
        surfaceDanger: Color = this.surfaceDanger,
        surfaceDangerVariant: Color = this.surfaceDangerVariant,
        surfaceWarning: Color = this.surfaceWarning,
        surfaceWarningVariant: Color = this.surfaceWarningVariant,
        contentOnNeutralXxHigh: Color = this.contentOnNeutralXxHigh,
        contentOnNeutralMedium: Color = this.contentOnNeutralMedium,
        contentOnNeutralLow: Color = this.contentOnNeutralLow,
        contentOnNeutralDanger: Color = this.contentOnNeutralDanger,
        contentOnNeutralWarning: Color = this.contentOnNeutralWarning,
        stateDefaultHover: Color = this.stateDefaultHover,
        stateDefaultFocus: Color = this.stateDefaultFocus
    ) = Colors(
        surfaceXHigh = surfaceXHigh,
        surfaceXLow = surfaceXLow,
        surfaceBrand = surfaceBrand,
        surfaceDanger = surfaceDanger,
        surfaceDangerVariant = surfaceDangerVariant,
        surfaceWarning = surfaceWarning,
        surfaceWarningVariant = surfaceWarningVariant,
        contentOnNeutralXxHigh = contentOnNeutralXxHigh,
        contentOnNeutralMedium = contentOnNeutralMedium,
        contentOnNeutralLow = contentOnNeutralLow,
        contentOnNeutralDanger = contentOnNeutralDanger,
        contentOnNeutralWarning = contentOnNeutralWarning,
        stateDefaultHover = stateDefaultHover,
        stateDefaultFocus = stateDefaultFocus
    )

    override fun toString(): String = "Colors"
}

internal fun lightColors() = Colors(
    surfaceXHigh = CoreGray500,
    surfaceXLow = CoreGray00,
    surfaceBrand = O2Blue500,
    surfaceDanger = CoreRed600,
    surfaceDangerVariant = CoreRed100,
    surfaceWarning = CoreYellow700,
    surfaceWarningVariant = CoreYellow100,
    contentOnNeutralXxHigh = CoreGray950,
    contentOnNeutralMedium = CoreGray500,
    contentOnNeutralLow = CoreGray300,
    contentOnNeutralDanger = CoreRed600,
    contentOnNeutralWarning = CoreRed700,
    stateDefaultHover = CoreAlphaDim50,
    stateDefaultFocus = CoreAlphaDim800
)

// TODO: not specified
internal fun darkColors() = Colors(
    surfaceXHigh = CoreGray500,
    surfaceXLow = CoreGray00,
    surfaceBrand = O2Blue500,
    surfaceDanger = CoreRed600,
    surfaceDangerVariant = CoreRed100,
    surfaceWarning = CoreYellow700,
    surfaceWarningVariant = CoreYellow100,
    contentOnNeutralXxHigh = CoreGray950,
    contentOnNeutralMedium = CoreGray500,
    contentOnNeutralLow = CoreGray300,
    contentOnNeutralDanger = CoreRed600,
    contentOnNeutralWarning = CoreRed700,
    stateDefaultHover = CoreAlphaDim50,
    stateDefaultFocus = CoreAlphaDim800
)

internal val LocalColors = staticCompositionLocalOf { lightColors() }