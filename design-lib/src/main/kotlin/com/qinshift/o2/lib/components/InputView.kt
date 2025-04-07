package com.qinshift.o2.lib.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.colors
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.qinshift.o2.lib.foundation.O2TaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputView(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = O2TaskTheme.typography.bodyM,
    label: String? = null,
    labelDescription: String? = null,
    placeholder: String? = null,
    minLines: Int = 1,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isError: Boolean = false,
    @DrawableRes trailingIconResId: Int? = null,
    trailingIconDescription: String? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    colors: InputViewColors = InputViewDefaults.colors()
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(O2TaskTheme.dimensions.spaceXS),
    ) {
        if (label != null || labelDescription != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(O2TaskTheme.dimensions.spaceXS),
            ) {
                label?.let {
                    Text(
                        text = it,
                        style = O2TaskTheme.typography.labelM,
                        color = labelColor(colors, isError),
                    )
                }
                labelDescription?.let {
                    Text(
                        text = it,
                        style = O2TaskTheme.typography.labelS,
                        color = colors.labelDescriptionTextColor,
                    )

                }
            }
        }
        BasicTextField(
            modifier = modifier
                .defaultMinSize(
                    minWidth = TextFieldDefaults.MinWidth,
                    minHeight = TextFieldDefaults.MinHeight
                ),
            value = value,
            enabled = enabled,
            minLines = minLines,
            textStyle = textStyle,
            maxLines = maxLines,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(cursorColor(colors, isError)),
            decorationBox = { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    placeholder = {
                        placeholder?.let {
                            Text(
                                it,
                                style = O2TaskTheme.typography.bodyM,
                                color = colors.placeholderTextColor,
                            )
                        }
                    },
                    value = value,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    isError = isError,
                    singleLine = singleLine,
                    trailingIcon = {
                        trailingIconResId?.let {
                            CompositionLocalProvider(
                                LocalContentColor provides colors.trailingIconColor,
                            ) {
                                TextFieldIcon(
                                    iconResource = it,
                                    onClick = onTrailingIconClick,
                                    description = trailingIconDescription,
                                )
                            }
                        }
                    },
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    container = {
                        OutlinedTextFieldDefaults.Container(
                            enabled = enabled,
                            isError = isError,
                            interactionSource = interactionSource,
                            modifier = Modifier,
                            colors = colors(
                                focusedBorderColor = colors.focusedBorderColor,
                                unfocusedBorderColor = colors.unfocusedBorderColor,
                                errorBorderColor = colors.errorBorderColor,
                            ),
                            shape = RoundedCornerShape(O2TaskTheme.dimensions.radiusInput),
                            focusedBorderThickness = 1.dp,
                            unfocusedBorderThickness = 1.dp,
                        )
                    },
                    contentPadding = contentPadding(
                        start = O2TaskTheme.dimensions.spaceM,
                        top = O2TaskTheme.dimensions.spaceS,
                        bottom = O2TaskTheme.dimensions.spaceS,
                        end = O2TaskTheme.dimensions.spaceXS
                    )
                )
            }
        )
    }
}

@Composable
internal fun TextFieldIcon(
    iconResource: Int,
    modifier: Modifier = Modifier,
    description: String? = null,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 40.dp)
            .padding(
                start = O2TaskTheme.dimensions.spaceXS,
                top = O2TaskTheme.dimensions.spaceXS,
                end = O2TaskTheme.dimensions.spaceXS,
                bottom = O2TaskTheme.dimensions.spaceXS
            )
            .run {
                onClick?.let {
                    clickable(
                        onClick = it,
                        enabled = true,
                        role = Role.Button,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = false,
                            radius = minOf(
                                40.dp,
                                40.dp
                            ) / 2
                        )
                    )
                } ?: this
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconResource),
            contentDescription = description,
            modifier = Modifier.size(24.dp)
        )
    }
}


private fun cursorColor(colors: InputViewColors, isError: Boolean): Color =
    if (isError) colors.errorBorderColor else colors.cursorColor

private fun labelColor(colors: InputViewColors, isError: Boolean): Color =
    if (isError) colors.errorLabelTextColor else colors.cursorColor


@Immutable
object InputViewDefaults {
    @Composable
    fun colors(
        focusedBorderColor: Color = O2TaskTheme.colors.surfaceXHigh,
        unfocusedBorderColor: Color = O2TaskTheme.colors.surfaceXHigh,
        errorBorderColor: Color = O2TaskTheme.colors.surfaceDanger,
        errorLabelTextColor: Color = O2TaskTheme.colors.surfaceDanger,
        labelTextColor: Color = O2TaskTheme.colors.contentOnNeutralXxHigh,
        labelDescriptionTextColor: Color = O2TaskTheme.colors.contentOnNeutralMedium,
        placeholderTextColor: Color = O2TaskTheme.colors.contentOnNeutralLow,
        trailingIconColor: Color = O2TaskTheme.colors.contentOnNeutralXxHigh,
        cursorColor: Color = O2TaskTheme.colors.contentOnNeutralXxHigh,
    ) = InputViewColors(
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        errorBorderColor = errorBorderColor,
        errorLabelTextColor = errorLabelTextColor,
        labelTextColor = labelTextColor,
        labelDescriptionTextColor = labelDescriptionTextColor,
        placeholderTextColor = placeholderTextColor,
        trailingIconColor = trailingIconColor,
        cursorColor = cursorColor,
    )
}

@Immutable
class InputViewColors(
    val focusedBorderColor: Color,
    val unfocusedBorderColor: Color,
    val errorBorderColor: Color,
    val errorLabelTextColor: Color,
    val labelTextColor: Color,
    val labelDescriptionTextColor: Color,
    val placeholderTextColor: Color,
    val trailingIconColor: Color,
    val cursorColor: Color,
) {
    fun copy(
        focusedBorderColor: Color = this.focusedBorderColor,
        unfocusedBorderColor: Color = this.unfocusedBorderColor,
        errorBorderColor: Color = this.errorBorderColor,
        errorLabelTextColor: Color = this.errorLabelTextColor,
        labelTextColor: Color = this.labelTextColor,
        labelDescriptionTextColor: Color = this.labelDescriptionTextColor,
        placeholderTextColor: Color = this.placeholderTextColor,
        trailingIconColor: Color = this.trailingIconColor,
        cursorColor: Color = this.cursorColor,
    ) =
        InputViewColors(
            focusedBorderColor.takeOrElse { this.focusedBorderColor },
            unfocusedBorderColor.takeOrElse { this.unfocusedBorderColor },
            errorBorderColor.takeOrElse { this.errorBorderColor },
            errorLabelTextColor.takeOrElse { this.errorLabelTextColor },
            labelTextColor.takeOrElse { this.labelTextColor },
            labelDescriptionTextColor.takeOrElse { this.labelDescriptionTextColor },
            placeholderTextColor.takeOrElse { this.placeholderTextColor },
            trailingIconColor.takeOrElse { this.trailingIconColor },
            cursorColor.takeOrElse { this.cursorColor },
        )
}