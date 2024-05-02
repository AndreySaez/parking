package domain.manager

import data.repository.ParkingRepositoryImpl
import domain.useCase.getInfoUseCase.GetCarByPlaceNumberUseCase
import domain.useCase.getInfoUseCase.GetHelpUseCase
import domain.useCase.getInfoUseCase.GetPlaceNumberByCarNumberUseCase
import domain.useCase.parkingUseCase.ParkUseCase
import domain.useCase.parkingUseCase.ReturnCarUseCase

class Manager {
    private val parkingRepository = ParkingRepositoryImpl()
    private val listOfCommands = listOf<Command>(
        ParkUseCase(parkingRepository),
        ReturnCarUseCase(parkingRepository),
        GetCarByPlaceNumberUseCase(parkingRepository),
        GetPlaceNumberByCarNumberUseCase(parkingRepository),
        GetHelpUseCase()
    )

    fun start() {
        println("Введите команду. Список команд: /help")
        while (true) {
            val input = readln()
            val command = listOfCommands.find { it.canHandle(input) }
            if (command != null) {
                command.handle(input)
            } else {
                println("Неверная команда")
            }
        }
    }
}