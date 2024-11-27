import java.util.Scanner
fun inputString() : String {
    val scanner = Scanner(System.`in`)
    var str : String
    do {
        str = scanner.nextLine()
        if (str.isEmpty()) inputError()
    } while(str.isEmpty())
    return str.trim()
}

fun inputInt() : Int {
    val scanner = Scanner(System.`in`)
    val str = scanner.nextLine()
    while(str.toIntOrNull() == null){
        inputError()
        scanner.nextLine()
    }
    return str.toInt()
}

fun inputError() {
    println("\nВведено некорректное значение! Повторите ввод!")
}