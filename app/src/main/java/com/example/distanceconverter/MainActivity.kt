package com.example.distanceconverter

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
import androidx.compose.ui.unit.dp
import com.example.distanceconverter.ui.theme.DistanceConverterTheme


val mileKerroin = 1.60934



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DistanceConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Converter(
                        mile = 1.609,
                        km = 0,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Converter(mile: Number, km: Number, modifier: Modifier = Modifier) {
    Text(
        text = "Convert from miles to kilometers",
        modifier = modifier.padding(16.dp)
    )
    Text(
        text = "$mile miles in kilometers is $km km",
        modifier = modifier.padding(30.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DistanceConverterTheme {
        Converter(1,2, modifier = Modifier)
    }
}