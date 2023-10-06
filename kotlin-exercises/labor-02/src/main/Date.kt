package main

import java.util.Calendar

data class Date(
    var year: Int = Calendar.getInstance().get(Calendar.YEAR) ,
    var month: Int = Calendar.getInstance().get(Calendar.MONTH) ,
    var day: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) ,
)
