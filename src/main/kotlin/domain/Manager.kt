package domain

import data.ParkingRepositoryImpl
import domain.useCase.getInfoUseCase.GetCarByPlaceNumberUseCase
import domain.useCase.getInfoUseCase.GetHelpUseCase
import domain.useCase.getInfoUseCase.GetPlaceNumberByCarNumberUseCase
import domain.useCase.parkingUseCase.ParkUseCase
import domain.useCase.parkingUseCase.ReturnCarUseCase

class Manager {
    fun start() {
        println("Введите команду. Список команд: /help")
        val parking = ParkUseCase(ParkingRepositoryImpl())
        val returnCar = ReturnCarUseCase(ParkingRepositoryImpl())
        val getPlace = GetCarByPlaceNumberUseCase(ParkingRepositoryImpl())
        val getCar = GetPlaceNumberByCarNumberUseCase(ParkingRepositoryImpl())
        val showHelp = GetHelpUseCase()
        while (true) {
            val input = readln()
            when {
                input.startsWith("/park") -> parking.park(input)
                input.startsWith("/return") -> returnCar.returnCar(input)
                input.startsWith("/show_car") -> getCar.getPlaceNumberByCarNumber(input)
                input.startsWith("/show_place") -> getPlace.getCarByPlaceNumber(input)
                input.startsWith("/help") -> showHelp.help(input)
                else -> println("Unknown command")
            }
        }
    }
}