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

    fun findFreePlace(parking: Map<String, Car?>): String? {
        parking.forEach {
            if (it.value == null) return it.key
        }

        return null
    }

}