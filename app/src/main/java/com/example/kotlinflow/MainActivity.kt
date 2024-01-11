package com.example.kotlinflow

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlinflow.ui.theme.KotlinFlowTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val channel = Channel<Int>()
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFlowTheme {

                //producer()
                //consumer()

                val job = GlobalScope.launch {
                    val data : Flow<Int> = producer()
                    data.collect{
                        Log.d("SAHIL-1", it.toString())
                    }
                }
                //This will cancel the above consumer running in coroutine after 3500ms.
                //GlobalScope.launch {
                //    delay(3500)
                //    job.cancel()
                //}
                GlobalScope.launch {
                    val data : Flow<Int> = producer()
                    delay(3500)
                    data.collect {
                        Log.d("SAHIL-2", it.toString())
                    }
                }
            }
        }
    }

    fun producer() = flow<Int> {
        val list = listOf<Int>(1,2,3,4,5,6,7,8,9,10)

        list.forEach {
            delay(1000)
            emit(it)
        }
    }
    /*
    fun producer() {
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
        }
    }

    fun consumer() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("SAHIL", channel.receive().toString())
            Log.d("SAHIL", channel.receive().toString())
        }
    }
    */
}
