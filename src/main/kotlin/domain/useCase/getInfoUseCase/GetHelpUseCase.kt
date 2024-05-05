package domain.useCase.getInfoUseCase

import domain.manager.Command

class GetHelpUseCase : Command {
    private fun help() {
        //Выводит список команд
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

    override fun canHandle(input: String): Boolean {
        return input == "/help"
    }

    override fun handle(input: String) {
        help()
    }
}