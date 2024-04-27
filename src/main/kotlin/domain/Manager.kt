package domain
import data.ParkingRepositoryImpl

class Manager {
    fun start() {
        val parking = ParkUseCase(ParkingRepositoryImpl())
        val parkingInfo = GetInfoUseCase(ParkingRepositoryImpl())
        while (true) {
            val input = readln()
            when {
                input.startsWith("/park") -> parking.park(input)
                input.startsWith("/return") -> parking.returnCar(input)
                input.startsWith("/show_car") -> parkingInfo.getPlaceNumberByCarNumber(input)
                input.startsWith("/show_place") -> parkingInfo.getCarByPlaceNumber(input)
                input.startsWith("/help") -> parkingInfo.help(input)
                else -> println("Unknown command")
            }
        }
    }
}