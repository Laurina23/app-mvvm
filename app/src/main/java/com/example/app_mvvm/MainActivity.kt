package com.example.app_mvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.app_mvvm.ui.theme.App_mvvmTheme
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_mvvmTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterView()
                }
            }
        }
    }

    @Composable
    fun CounterView(counterVM: CounterViewModel = viewModel()) {
        val counterState = counterVM.counter.value
        val integer = GenericClass(45)
        val string = GenericClass("Hello")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Current counter value: ${counterState.count}")
            Row {
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    counterVM.incrementCounter()
                }) {
                    Text(text = "Increment Value")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    counterVM.decrementCounter()
                }) {
                    Text(text = "Decrement Value")
                }
                Spacer(modifier = Modifier.width(8.dp))

            }
            Button(onClick = {
                counterVM.resetCounter()
                integer.printContent()
                string.printContent()
            }) {
                Text(text = "Reset Value")
            }
        }
    }
}

data class Counter(val count: Int)

class CounterViewModel : ViewModel() {
    private val _counter = mutableStateOf(Counter(0))
    val counter: State<Counter> = _counter
    fun incrementCounter() {
        _counter.value = Counter(_counter.value.count + 1)
    }
    fun decrementCounter() {
        _counter.value = Counter(_counter.value.count - 1)
    }
    fun resetCounter() {
        _counter.value = Counter(0)
    }
}
class GenericClass<T>(var content: T) {
    fun printContent() {
        Log.d("GenericClass", content.toString())
    }
}
