package domain.useCase.getInfoUseCase

import data.ParkingRepository
import data.findCarByNumber
import domain.manager.Command

class GetPlaceNumberByCarNumberUseCase(private val parkingRepository: ParkingRepository) : Command {
    private val regex = Regex("/show_car [a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3}")
    private fun getPlaceNumberByCarNumber(input: String) {
        val words = input.split(" ").filter { it != "/show_car" }
        val carNumber = words[0]
        parkingRepository.findCarByNumber(carNumber)?.let {
            println("Машина $carNumber на месте ${it.first}")
        } ?: println("Такой машины нет на парковке")
    }

    override fun canHandle(input: String): Boolean {
        return regex.matches(input)
    }

    override fun handle(input: String) {
        getPlaceNumberByCarNumber(input)
    }

}