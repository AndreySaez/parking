package domain
import data.Car
import data.Owner

class ParkUseCase(private val parkingRepository: ParkingRepository) {
    fun park(input: String) {
        val regex = Regex("/park [a-zA-Z ]+[a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3} [a-zA-Z ]+[a-zA-Z ]")
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