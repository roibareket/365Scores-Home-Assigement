package com.bareket.gamescore.ui.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toPrettyDate() =  SimpleDateFormat("dd/MM HH:mm", Locale.getDefault()) .format(this)