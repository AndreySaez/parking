package domain.useCase.getInfoUseCase

class GetHelpUseCase() {
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