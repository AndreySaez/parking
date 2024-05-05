package domain

import data.Car

object Parking {

    fun create(): MutableMap<String, Car?> {
        val parking = mutableMapOf<String, Car?>()
        for (i in 1..3) {
            parking["P$i"] = null
        }
        return parking
    }
}