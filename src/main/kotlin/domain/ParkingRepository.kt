package domain

import data.Car

interface ParkingRepository {
    val places: Iterable<Pair<String, Car?>>
    fun putCar(place: String, car: Car)
    fun dropCar(place: String)
}

fun ParkingRepository.findFreePlaces(): String? {
    places.forEach {
        if (it.second == null) return it.first
    }
    return null
}

fun ParkingRepository.findCarByNumber(number: String): Pair<String, Car?>? {
    return places.find {
        it.second?.number == number
    }
}

fun ParkingRepository.findPlaceNumber(placeNumber: String): Pair<String, Car?>? {
    return places.find { it.first == placeNumber }
}