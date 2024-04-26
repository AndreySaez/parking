import data.ParkingRepositoryImpl
import domain.Manager

fun main(args: Array<String>) {
    println("Введите команду. Список команд: /help")
    val manager = Manager(ParkingRepositoryImpl())

    while (true) {
        val input = readln()
        when {
            input.startsWith("/park") -> manager.park(input)
            input.startsWith("/return") -> manager.returnCar(input)
            input.startsWith("/show_car") -> manager.getPlaceNumberByCarNumber(input)
            input.startsWith("/show_place") -> manager.getCarByPlaceNumber(input)
            input.startsWith("/help") -> manager.help(input)
            else -> println("Unknown command")
        }
    }
}


