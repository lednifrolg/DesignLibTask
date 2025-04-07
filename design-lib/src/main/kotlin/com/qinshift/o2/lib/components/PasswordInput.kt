package com.qinshift.o2.lib.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.qinshift.o2.lib.R
import com.qinshift.o2.lib.foundation.O2TaskTheme

@Composable
fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: InputViewColors = InputViewDefaults.colors(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Unspecified, autoCorrectEnabled = false,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Unspecified
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    validationRules: List<ValidationRule> = defaultPasswordValidationRules,
    onValidationChange: (Boolean) -> Unit = {},
    labelDescription: String? = null,
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var validationResults by rememberSaveable {
        mutableStateOf(
            mapOf(
                "minLength" to false,
                "uppercase" to false,
                "digit" to false,
                "specialChar" to false
            )
        )
    }

    val isPasswordValid = validationResults.values.all { it }

    LaunchedEffect(isPasswordValid) {
        onValidationChange(isPasswordValid)
    }

    Column(modifier = modifier) {
        InputView(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = {
                onValueChange(it)
                validationResults = validatePassword(it, validationRules)
            },
            label = label,
            labelDescription = labelDescription,
            enabled = enabled,
            maxLines = maxLines,
            singleLine = singleLine,
            textStyle = if (isPasswordVisible) O2TaskTheme.typography.bodyM else O2TaskTheme.typography.bodyM.copy(
                fontSize = 10.sp,
                letterSpacing = 0.3.em
            ),
            isError = isError,
            trailingIconResId = if (isPasswordVisible) R.drawable.icon_show else R.drawable.icon_hide,
            onTrailingIconClick = { isPasswordVisible = !isPasswordVisible },
            trailingIconDescription = if (isPasswordVisible) "Hide password" else "Show password",
            placeholder = placeholder,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(
                '●'
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            colors = colors,
        )

        validationRules.forEach {
            validationResults[it.name]?.let { isValid ->
                ValidationRule(isValid, it.message)
            }
        }
    }
}

@Composable
fun ValidationRule(isPassed: Boolean, text: String) {
    AnimatedStrikethroughText(
        text = text,
        isVisible = isPassed,
        modifier = Modifier.padding(top = 2.dp),
        strikethroughStyle = SpanStyle(
            color = O2TaskTheme.colors.contentOnNeutralLow
        ),
        textStyle = O2TaskTheme.typography.labelS.copy(
            color = O2TaskTheme.colors.contentOnNeutralXxHigh,
        )
    )
}

data class ValidationRule(val name: String, val isValid: (String) -> Boolean, val message: String)

val defaultPasswordValidationRules = listOf(
    ValidationRule("minLength", { it.length >= 8 }, "● at least 8 characters"),
    ValidationRule(
        "uppercase",
        { it.any { char -> char.isUpperCase() } },
        "● at least 1 uppercase letter"
    ),
    ValidationRule("digit", { it.any { char -> char.isDigit() } }, "● at least 1 number"),
    ValidationRule(
        "specialChar",
        { it.any { char -> char in "?=#/%" } },
        "● at least 1 special character (? = # / %)"
    ),
)

private fun validatePassword(password: String, rules: List<ValidationRule>): Map<String, Boolean> {
    return rules.associate { rule ->
        rule.name to rule.isValid(password)
    }
}