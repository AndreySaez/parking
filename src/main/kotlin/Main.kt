import domain.Manager

fun main(args: Array<String>) {
    println("Введите команду. Список команд: /help")
    val manager = Manager()

    while (true) {
        val input = readln()
        when {
            input.startsWith("/park") -> manager.park(input)
            input.startsWith("/return") -> manager.returnCar(input)
            input.startsWith("/show_car") -> manager.parkInfoByCar(input)
            input.startsWith("/show_place") -> manager.parkInfoByPlace(input)
            input.startsWith("/help") -> manager.help(input)
            else -> println("Unknown command")
        }
    }
}


