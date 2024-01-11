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
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val channel = Channel<Int>()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFlowTheme {


                //Channels
                //producer()
                //consumer()

                //Methods
                //val job = GlobalScope.launch {
                //    val data : Flow<Int> = producer()
                //        //This function is called to run the code that you want to execute before the start
                //       //of the flow.
                //        .onStart {
                //            Log.d("SAHIL","Starting out")
                //        }
                //        //This function is called to run the code that you want to execute after the
                //        //completion of the flow.
                //        .onCompletion {
                //            Log.d("SAHIL", "Completed")
                //        }
                //        //This function is called to run the code that you want to execute after
                //        //each iteration.
                //       .onEach {
                //            Log.d("SAHIL","About to emit -> $it")
                //        }
                //    data.collect{
                //        Log.d("SAHIL", it.toString())
                //    }
                //}

                //This will cancel the above consumer running in coroutine after 3500ms.
                //GlobalScope.launch {
                //    delay(3500)
                //    job.cancel()
                //}
                //GlobalScope.launch {
                //    val data : Flow<Int> = producer()
                //    delay(3500)
                //    data.collect {
                //        Log.d("SAHIL-2", it.toString())
                //    }


                //Non-Terminal Operator
                //GlobalScope.launch(Dispatchers.Main) {
//                    producer()
//                        .map {
//                            it * 2
//                        }
//                        .filter {
//                            it < 8
//                        //}
//                //        .buffer(3)
//                 //       .collect {
//                 //           delay(1500)
//                 //           Log.d("SAHIL", it.toString())
//                 //       }
//                //}

                //FlowOn - Context Preservation
                // All the functions above the flowOn method will run on the IO thread [As in the case]
                GlobalScope.launch(Dispatchers.Main) {
                    producer()
                        .map {
                            delay(1000)
                            it *2
                            Log.d("SAHIL", "Map Thread :-${Thread.currentThread().name}")
                        }
                        .filter {
                            delay(1000)
                            Log.d("SAHIL","Filter Thread :- ${Thread.currentThread().name}")
                            it < 8
                        }
                        .flowOn(Dispatchers.IO)
                        .collect {
                            Log.d("SAHIL", "Collector Thread :- ${Thread.currentThread().name}")
                        }
                }


            }
        }
    }
}

fun producer() = flow<Int> {
    val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    list.forEach {
        delay(1000)
        Log.d("SAHIL", "Emitter Thread :- ${Thread.currentThread().name}")
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
