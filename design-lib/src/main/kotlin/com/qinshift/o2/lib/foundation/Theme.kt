package com.qinshift.o2.lib.foundation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun O2TaskTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    O2TaskTheme.isThemeInDarkMode = useDarkTheme
    ProvideColorsDimensions(useDarkTheme) {
        ProvideColorAwareTypography {
            MaterialTheme(
                colorScheme = if (useDarkTheme) darkColorScheme() else lightColorScheme(),
                content = content
            )
        }
    }
}

@Composable
private fun ProvideColorsDimensions(
    useDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) darkColors() else lightColors()

    CompositionLocalProvider(
        LocalDimensions provides O2TaskTheme.dimensions,
        LocalColors provides colors
    ) {
        content()
    }
}

@Composable
private fun ProvideColorAwareTypography(content: @Composable () -> Unit) {
    val typography = colorAwareTypography(
        textPrimary = O2TaskTheme.colors.contentOnNeutralXxHigh,
    )

    CompositionLocalProvider(
        LocalTypography provides typography
    ) {
        content()
    }
}

object O2TaskTheme {

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val dimensions: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    var isThemeInDarkMode: Boolean = false
        internal set
}