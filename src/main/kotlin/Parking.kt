import data.Car

class Parking {

    fun create(): MutableMap<String, Car?> {
        val parking = mutableMapOf<String, Car?>()
        for (i in 1..3) {
            parking["P$i"] = null
        }
        return parking
    }

}