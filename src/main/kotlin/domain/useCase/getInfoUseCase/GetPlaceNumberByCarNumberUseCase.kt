package domain.useCase.getInfoUseCase

import domain.ParkingRepository
import domain.findCarByNumber

class GetPlaceNumberByCarNumberUseCase(private val parkingRepository: ParkingRepository) {
    fun getPlaceNumberByCarNumber(input: String) {
        //Печатает место, где припаркованна машина,по ее номеру
        val regex = Regex("/show_car [a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3}")
        if (regex.matches(input)) {
            val words = input.split(" ").filter { it != "/show_car" }
            val carNumber = words[0]
            parkingRepository.findCarByNumber(carNumber)?.let {
                println("Машина $carNumber на месте ${it.first}")
            } ?: println("Такой машины нет на парковке")
        } else {
            println("Что-то пошло не так. Проверьте правильность ввода: /show_car Номер(А111АА777)")
        }
    }

}