@file:Suppress("UNREACHABLE_CODE")

import domain.Manager

fun main(args: Array<String>) {
    println("Введите команду. Список команд: /help")

    //domain.Manager().park()
    // domain.Manager().returnCar()
    //domain.Manager().parkInfoByCar()
    Manager().parkInfoByPlace()
}


