package com.rzrasel.usagesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rzrasel.logwriter.LogWriter
import com.rzrasel.usagesapplication.ui.theme.UsagesApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsagesApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LogWriter.log("Debug log test Class→MainActivity Method→setContent")
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    LogWriter.log("Debug log test Class→MainActivity Method→Greeting")
    LogMe().onLog()
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

class LogMe {
    fun onLog() {
        LogWriter.log("Debug log test Class→LogMe Method→onLog")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UsagesApplicationTheme {
        Greeting("Android")
    }
}