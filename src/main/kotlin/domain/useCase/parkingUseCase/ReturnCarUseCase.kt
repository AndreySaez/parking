package domain.useCase.parkingUseCase

import data.Owner
import data.ParkingRepository
import domain.manager.Command

class ReturnCarUseCase(private val parkingRepository: ParkingRepository) : Command {
    private val regex = Regex("/return [a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3} [a-zA-Z ]+[a-zA-Z ]")
    private fun returnCar(input: String) {
        val words = input.split(" ").filter { it != "/return" }
        val carNumber = words[0]
        val owner = Owner(
            name = words[1],
            lastName = words[2],
        )
        parkingRepository.places.find {
            it.second?.number == carNumber
        }?.also {
            if (it.second?.owner == owner) {
                parkingRepository.dropCar(it.first)
                println("Место ${it.first} освобождено")
            } else {
                println("Вы не владелец машины")
            }
        } ?: run {
            println("Такой машины нет на парковке")
        }
    }

    override fun canHandle(input: String): Boolean {
        return regex.matches(input)
    }

    override fun handle(input: String) {
        returnCar(input)
    }
}