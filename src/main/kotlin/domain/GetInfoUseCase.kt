package domain

class GetInfoUseCase(private val parkingRepository: ParkingRepository) {
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

    fun help(input: String) {
        //Выводит список команд
        if (input == "/help") {
            println(
                """
         Список доступных команд:
         /park
         /return
         /show_car
         /show_place
        """.trimIndent()
            )
        }
    }
}