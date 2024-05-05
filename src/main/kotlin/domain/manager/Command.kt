package domain.manager

interface Command {
    fun canHandle(input: String) : Boolean
    fun handle(input: String)
}