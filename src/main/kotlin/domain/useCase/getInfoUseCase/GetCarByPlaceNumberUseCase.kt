package domain.useCase.getInfoUseCase

import data.repository.ParkingRepository
import data.repository.findPlaceNumber
import domain.manager.Command

class GetCarByPlaceNumberUseCase(private val parkingRepository: ParkingRepository) : Command {
    private val regex = Regex("/show_place P\\d")
    private fun getCarByPlaceNumber(input: String) {
        val words = input.split(" ").filter { it != "/show_place" }
        val placeNumber = words[0]
        parkingRepository.findPlaceNumber(placeNumber)?.let {
            println("На месте $placeNumber находится машина ${it.second}")
        } ?: println("Такого парковочного места нет")
    }

    override fun canHandle(input: String): Boolean {
        return regex.matches(input)
    }

    override fun handle(input: String) {
        getCarByPlaceNumber(input)
    }
}