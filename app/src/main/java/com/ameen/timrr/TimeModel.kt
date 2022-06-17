package com.ameen.timrr

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class TimeModel : ViewModel() {

    var time = MutableLiveData("00:00:000")
    private  var lastMilliSecs = 0L
    private  var lastTimeStamp = 0L
    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isCountingEnabled = false

    @RequiresApi(Build.VERSION_CODES.O)
    fun startTimer(){
        lastTimeStamp = System.currentTimeMillis()
        coroutineScope.launch {
            isCountingEnabled= true
            while (isCountingEnabled){
                delay(10L)
                lastMilliSecs += System.currentTimeMillis() - lastTimeStamp
                lastTimeStamp = System.currentTimeMillis()
                time.value = timeFormatter(lastMilliSecs)
            }
        }
    }

    fun pauseTimer(){
        isCountingEnabled = false
    }

    fun resetTimer(){
        lastTimeStamp = 0
        lastMilliSecs = 0
        coroutineScope.cancel()
        time.value = "00:00:000"
        coroutineScope = CoroutineScope(Dispatchers.Main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun timeFormatter(value : Long) : String{
        val localDateFormatter = LocalDateTime.ofInstant(Instant.ofEpochMilli(value),ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("mm:ss.SSS", Locale.getDefault())
        return localDateFormatter.format(formatter)
    }
}