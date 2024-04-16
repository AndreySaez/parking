package domain

import data.Car
import data.Owner


class Manager {


    private val parking = Parking.create()

    fun park() {
        while (true) {
            val input = readln()
            val regex = Regex("/park [a-zA-Z ]+[a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3} [a-zA-Z ]+[a-zA-Z ]")
            if (regex.matches(input)) {
                val words = input.split(" ").filter { it != "/park" }
                val filteredWords = words.dropLast(2)
                val freePlace = Parking.findFreePlace(parking)
                if (freePlace != null) {
                    parking[freePlace] = Car(
                        color = filteredWords[0],
                        model = filteredWords[1],
                        number = filteredWords[2],
                        owner = Owner(name = words[3], lastName = words[4])
                    )
                    println(parking.toString())
                } else {
                    println("Все места закончились")
                }
            } else {
                println("Проверьте правильность ввода: /park Цвет Марка Номер(А111АА777) Имя Фамилия")
            }
        }
    }

    fun returnCar() {
        while (true) {
            val input = readln()
            val regex = Regex("/return [a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3} [a-zA-Z ]+[a-zA-Z ]")
            if (regex.matches(input)) {
                val words = input.split(" ").filter { it != "/return" }
                val carNumber = words[0]
                val owner = Owner(
                    name = words[1],
                    lastName = words[2],
                )
                parking.entries.find {
                    it.value?.number == carNumber
                }?.also {
                    if (it.value?.owner == owner) {
                        parking[it.key] = null
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


    fun parkInfoByCar() {
        //Печатает место, где припаркованна машина,по ее номеру
        while (true) {
            val input = readln()
            val regex = Regex("/show_car [a-zA-Z ]\\d{3}[a-zA-Z]{2}\\d{2,3}")
            if (regex.matches(input)) {
                val words = input.split(" ").filter { it != "/show_car" }
                val carNumber = words[0]
                parking.entries.find {
                    it.value?.number == carNumber
                }?.let {
                    println(it.key)
                } ?: println("Такой машины нет на парковке")
            } else {
                println("Что-то пошло не так. Проверьте правильность ввода: /show_car Номер(А111АА777)")
            }
        }
    }

    fun parkInfoByPlace() {
        enableTestParking()
        //Печатает информацию о машине по месту на парковке
        while (true) {
            val input = readln()
            val regex = Regex("/show_place P\\d")
            if (regex.matches(input)) {
                val words = input.split(" ").filter { it != "/show_place" }
                val placeNumber = words[0]
                parking.entries.find {
                    it.key == placeNumber
                }?.let {
                    println(it.value)
                } ?: println("Такого парковочного места нет")
            } else {
                println("Что-то пошло не так. Проверьте правильность ввода: /show_place P1 - P${parking.size}")
            }
        }
    }

    fun help() {
        //Выводит список команд
        val input = readln()
        if (input == "/help") {
            println(
                """
         Список доступных команд:
         /park
         /return_car
         /park_info_car
         /park_info_place
        """.trimIndent()
            )
        }
    }

    private fun enableTestParking() {
        parking["P1"] = Car(
            color = "Red",
            model = "Ford",
            number = "A777AA777",
            owner = Owner(
                name = "Andrey",
                lastName = "Sirotin"
            )
        )
        parking["P2"] = Car(
            color = "Red",
            model = "Ford",
            number = "A777AB777",
            owner = Owner(
                name = "Andrey",
                lastName = "Sirotin"
            )
        )
        parking["P3"] = Car(
            color = "Red",
            model = "Ford",
            number = "A777AC777",
            owner = Owner(
                name = "Andrey",
                lastName = "Sirotin"
            )
        )
    }
}