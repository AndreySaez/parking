package domain.useCase.parkingUseCase

import data.Car
import data.Owner
import data.ParkingRepository
import data.findFreePlaces
import domain.manager.Command

class ParkUseCase(private val parkingRepository: ParkingRepository) : Command {
    private val regex = Regex("/park [a-zA-Z]+ [a-zA-Z]+ [a-zA-Z][0-9]{3}[a-zA-Z]{2}[0-9]{2,3} [a-zA-Z]+ [a-zA-Z]+")
    private fun park(input: String) {
        val words = input.split(" ").filter { it != "/park" }
        val filteredWords = words.dropLast(2)
        val freePlace = parkingRepository.findFreePlaces()
        if (freePlace != null) {
            parkingRepository.putCar(
                freePlace, Car(
                    color = filteredWords[0],
                    model = filteredWords[1],
                    number = filteredWords[2],
                    owner = Owner(name = words[3], lastName = words[4])
                )
            )
            println("$filteredWords была припаркована на место $freePlace")
        } else {
            println("Все места закончились")
        }
    }

    override fun canHandle(input: String): Boolean {
        return regex.matches(input)
    }

    override fun handle(input: String) {
        park(input)
    }
}