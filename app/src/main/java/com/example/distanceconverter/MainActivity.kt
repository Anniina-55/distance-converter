package com.example.distanceconverter
import androidx.compose.ui.res.stringResource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.distanceconverter.ui.theme.DistanceConverterTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // compose UI definition
        setContent {
            DistanceConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Converter( modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
// UI
@Composable
fun Converter(modifier: Modifier = Modifier) {
    val mileMultiplier = 1.609 // defining variable for multiplier
    var input by remember { mutableStateOf("") } // state variable for user input

    val miles = input.toDoubleOrNull() // convert user input to number (text field handles strings)
    // conversion calculation if miles are inputted correctly
    val km = if (miles != null && miles > 0 ) {
        miles * mileMultiplier
    } // if conversion isn't possible and input is invalid
        else { null }
    // if input is negative
    val negativeInput = miles != null && miles < 0

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White) // white background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        // heading
        Text(text = stringResource(id = R.string.heading),
            fontSize = 22.sp
            )
        // input field
        OutlinedTextField(
            value = input,
            onValueChange = {input = it},
            // numpad
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            label = { Text(text= stringResource(id = R.string.label)) },
            modifier = Modifier
                .fillMaxWidth() // full width
                .background(Color.White) // same as input field container
                .padding(top = 16.dp, bottom = 16.dp ),
            // purple outlines for input field
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF8A58BB),
                unfocusedIndicatorColor = Color(0xFF7D5CA1),
                // label colors
                focusedLabelColor = Color(0xFF8A58BB),
                unfocusedLabelColor = Color(0xFF7D5CA1),
                // input fields background color
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
                )
        )
        // display conversion result and possible error message
        if (km != null && miles != null) {
            Text(text = stringResource(id = R.string.result, miles, km))
        } else if (negativeInput) {
            Text(text= stringResource(id = R.string.error))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConverterPreview() {
    DistanceConverterTheme {
        Converter()
    }
}