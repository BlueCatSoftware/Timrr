package com.ameen.timrr

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ameen.timrr.ui.theme.TimrrTheme
import kotlin.time.ExperimentalTime

class MainActivity : ComponentActivity() {
    val timeModel by viewModels<TimeModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimrrTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    backgroundColor = MaterialTheme.colors.background,
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Timrr")
                        })
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Greeting(timeModel, this)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalTime::class)
@Composable
fun Greeting(viewModel: TimeModel, context: Context) {
    val count by viewModel.time.observeAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = count.toString(),
            Modifier.padding(2.dp),
            fontFamily = FontFamily.Default,
            fontSize = 50.sp
        )

        Spacer(Modifier.height(8.dp))
        Text(
            "click button to start timer",
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 50.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier.clip(CircleShape)
                        .height(70.dp)
                        .width(70.dp)
                        .clickable {
                            viewModel.startTimer()
                        },
                    backgroundColor = Color.Red,

                    ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Start",
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(70.dp)
                        .width(70.dp)
                        .clickable {
                            viewModel.resetTimer()
                        },
                    backgroundColor = Color.Red,

                    ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Reset",
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(70.dp)
                        .width(70.dp)
                        .clickable {
                            viewModel.pauseTimer()
                        },
                    backgroundColor = Color.Red,

                    ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Pause",
                            color = Color.White
                        )
                    }
                }
            }

        }

    }
}