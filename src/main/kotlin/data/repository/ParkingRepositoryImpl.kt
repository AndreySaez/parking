package data.repository

import data.Car
import domain.Parking

class ParkingRepositoryImpl : ParkingRepository {

    private val parking = Parking.create()

    override val places: Iterable<Pair<String, Car?>> get() = parking.entries.map { Pair(it.key, it.value) }
    override fun putCar(place: String, car: Car) {
        parking[place] = car
    }

    override fun dropCar(place: String) {
        parking[place] = null
    }
}