package com.manriquetavi.bakeryapp.util

import java.text.DecimalFormat
import java.util.*

fun calculateTimeEstimated(date: Date): String {
    val timeSplit = date.toString().split(" ")[3].split(":")
    val hour = timeSplit[0].toInt()
    val minute = timeSplit[1].toInt()
    var hourEstimated1: Int = hour
    var minuteEstimated1: Int = minute + 30
    if (minuteEstimated1 > 60) {
        minuteEstimated1 -= 60
        hourEstimated1 += 1
    }
    var hourEstimated2: Int = hour
    var minuteEstimated2: Int = minute + 45
    if (minuteEstimated2 >60) {
        minuteEstimated2 -= 60
        hourEstimated2 += 1
    }
    return "${formatTwoDigits(hourEstimated1)}:${formatTwoDigits(minuteEstimated1)} - ${formatTwoDigits(hourEstimated2)}:${formatTwoDigits(minuteEstimated2)}"
}

fun formatTwoDigits(int: Int): String {
    return DecimalFormat("00").format(int)
}
