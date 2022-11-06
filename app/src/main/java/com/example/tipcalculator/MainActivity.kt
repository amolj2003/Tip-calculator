package com.example.tipcalculator

import android.os.Bundle
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import kotlinx.coroutines.test.withTestContext
import java.text.NumberFormat
import java.time.temporal.TemporalAmount

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen(){
    var amountInput by remember {
        mutableStateOf( "")
    }
    val amount = amountInput.toDoubleOrNull()?:0.0
    val tip = calulateTip(amount)
    Column(modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        EditNumberField(value =amountInput, onValueChange = {amountInput=it})
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(id = R.string.tip_amount,tip),
        modifier = Modifier.align(Alignment.CenterHorizontally),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold)

    }

}

@Composable
fun EditNumberField(value:String,onValueChange:(String)->Unit ){


    TextField(value = value,
        onValueChange = onValueChange,
        label={
            Text(text = stringResource(id = R.string.bill_amount),
            modifier = Modifier.fillMaxSize() )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )

}
private fun calulateTip(amount: Double,tipPercent:Double=15.0):String{
    val tip = tipPercent / 100* amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    TipCalculatorTheme{
        TipTimeScreen()
    }
}