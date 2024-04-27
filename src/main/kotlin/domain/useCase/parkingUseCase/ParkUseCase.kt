package domain.useCase.parkingUseCase

import data.Car
import data.Owner
import domain.ParkingRepository
import domain.findFreePlaces

class ParkUseCase(private val parkingRepository: ParkingRepository) {
    fun park(input: String) {
        val regex = Regex("/park [a-zA-Z]+ [a-zA-Z]+ [a-zA-Z][0-9]{3}[a-zA-Z]{2}[0-9]{3} [a-zA-Z]+ [a-zA-Z]+")
        if (regex.matches(input)) {
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
        } else {
            println("Проверьте правильность ввода: /park Цвет Марка Номер(А111АА777) Имя Фамилия")
        }
    }
}