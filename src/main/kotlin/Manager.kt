import com.sun.tools.javac.Main
import data.Car
import data.Owner


class Manager {




        fun park() {

        }

        fun returnCar() {
            //Возвращает машину владельцу и только ему и освобождает место на парковке
            println("Возвращает машину владельцу и только ему и освобождает место на парковке")
        }

        fun parkInfoByCar() {
            //Возвращает место, где припаркованна машина, по ее номер
            println("Возвращает место, где припаркованна машина, по ее номер")
        }

        fun parkInfoByPlace() {
            //Возвращает информацию о машине по месту на парковке

            println("Возвращает информацию о машине по месту на парковке")
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
    }