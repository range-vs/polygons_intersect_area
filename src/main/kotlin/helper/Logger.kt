package helper

interface ILogger{
    fun log(msg: String)
}

class Logger: ILogger {

    override fun log(msg: String) {
        println("Logger:\n$msg")
    }

}
