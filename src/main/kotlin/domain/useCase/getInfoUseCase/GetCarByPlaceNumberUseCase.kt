package domain.useCase.getInfoUseCase

import domain.ParkingRepository
import domain.findPlaceNumber

class GetCarByPlaceNumberUseCase(private val parkingRepository: ParkingRepository) {
    fun getCarByPlaceNumber(input: String) {
        //Печатает информацию о машине по месту на парковке
        val regex = Regex("/show_place P\\d")
        if (regex.matches(input)) {
            val words = input.split(" ").filter { it != "/show_place" }
            val placeNumber = words[0]
            parkingRepository.findPlaceNumber(placeNumber)?.let {
                println("На месте $placeNumber находится машина ${it.second}")
            } ?: println("Такого парковочного места нет")
        } else {
            println("Что-то пошло не так. Проверьте правильность ввода: /show_place P1 - PN")
        }
    }
}