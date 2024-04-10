import data.Car
import data.Owner

fun main(args: Array<String>) {
    println("Введите команду. Список команд: /help")

    val parking = mutableMapOf<String, Car?>()
    for (i in 1..3) {
        parking["P$i"] = null
    }
    while (true) {
        parking.forEach { it ->
            if (it.value == null) {
                val input = readln()
                val regex = Regex("/park [a-zA-Z ]+[a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3} [a-zA-Z ]+[a-zA-Z ]")
                if (regex.matches(input)) {
                    val words = input.split(" ").filter { it != "/park" }
                    val filteredWords = words.dropLast(2)
                    val owner = Owner(name = words[3], lastName = words[4])
                    val newCar = Car(
                        color = filteredWords[0],
                        model = filteredWords[1],
                        number = filteredWords[2],
                        owner = owner
                    )
                    parking[it.key] = newCar
                    println("На парковку был препаркован автомобиль ${filteredWords.joinToString(" ")}")
                    println(parking)
                } else println("Что-то пошло не так. Проверьте правильность ввода: /park Цвет Марка Номер(А111АА777) Имя Фамилия")
            } else if (parking[it.key] != null ){
                println("Место ${it.key} Занято")
            }
        }
        Manager().help()
    }
}

