package domain.manager

import data.ParkingRepositoryImpl
import domain.useCase.getInfoUseCase.GetCarByPlaceNumberUseCase
import domain.useCase.getInfoUseCase.GetHelpUseCase
import domain.useCase.getInfoUseCase.GetPlaceNumberByCarNumberUseCase
import domain.useCase.parkingUseCase.ParkUseCase
import domain.useCase.parkingUseCase.ReturnCarUseCase

class Manager {
    fun start() {
        println("Введите команду. Список команд: /help")
        val parkCommand = ParkUseCase(ParkingRepositoryImpl())
        val returnCarCommand = ReturnCarUseCase(ParkingRepositoryImpl())
        val getPlaceCommand = GetCarByPlaceNumberUseCase(ParkingRepositoryImpl())
        val getCarCommand = GetPlaceNumberByCarNumberUseCase(ParkingRepositoryImpl())
        val getHelpCommand = GetHelpUseCase()
        while (true) {
            val input = readln()
            when {
                parkCommand.canHandle(input) -> parkCommand.handle(input)
                returnCarCommand.canHandle(input) -> returnCarCommand.handle(input)
                getCarCommand.canHandle(input) -> getCarCommand.handle(input)
                getPlaceCommand.canHandle(input) -> getPlaceCommand.handle(input)
                getHelpCommand.canHandle(input) -> getHelpCommand.handle(input)
                else -> println("Unknown command")
            }
        }
    }
}