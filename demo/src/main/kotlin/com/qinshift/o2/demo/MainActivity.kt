package com.qinshift.o2.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qinshift.o2.lib.components.InputView
import com.qinshift.o2.lib.components.PasswordInput
import com.qinshift.o2.lib.foundation.O2TaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            O2TaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Demo(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Demo(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        DemoPassword()
        DemoInput()
    }
}

@Composable
fun DemoPassword() {
    var value by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

    PasswordInput(
        value = value,
        onValueChange = { value = it },
        onValidationChange = { isError = !it },
        placeholder = "Placeholder",
        label = "Password",
        labelDescription = "Required",
        isError = isError && value.isNotBlank(),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    )
}

@Composable
fun DemoInput() {
    var value by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

    InputView(
        value = value,
        onValueChange = { value = it },
        placeholder = "Placeholder Error",
        isError = isError,
        enabled = false,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}

