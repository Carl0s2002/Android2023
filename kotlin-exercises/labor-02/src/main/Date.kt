package main

import java.util.Calendar

data class Date(
    var year: Int = Calendar.getInstance().get(Calendar.YEAR) ,
    var month: Int = Calendar.getInstance().get(Calendar.MONTH) ,
    var day: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) ,
) : Comparable<Date> {
    override fun compareTo(other: Date): Int {
        // Compare years
        if (year != other.year) {
            return year - other.year
        }

        // If years are equal, compare months
        if (month != other.month) {
            return month - other.month
        }

        // If months are equal, compare days
        return day - other.day
    }
}
