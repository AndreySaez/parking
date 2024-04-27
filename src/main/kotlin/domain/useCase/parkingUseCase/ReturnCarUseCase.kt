package domain.useCase.parkingUseCase

import data.Owner
import domain.ParkingRepository

class ReturnCarUseCase(private val parkingRepository: ParkingRepository) {
    fun returnCar(input: String) {
        val regex = Regex("/return [a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3} [a-zA-Z ]+[a-zA-Z ]")
        if (regex.matches(input)) {
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
        } else {
            println("Что-то пошло не так. Проверьте правильность ввода: /return Номер(А111АА777) Имя Фамилия")
        }
    }
}