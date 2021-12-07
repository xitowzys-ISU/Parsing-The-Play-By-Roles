import java.io.File
import java.io.FileReader
import java.io.BufferedReader

fun printTextPerRole(roles: ArrayList<String>, textLines: ArrayList<String>): String {
    val result = StringBuilder()

    for (role in roles) {
        result.append("${role}:\n")
        var lineNumber = 1
        for (textLine in textLines) {
            if (textLine.startsWith("$role:")) {
                val splitArr = textLine.substring(textLine.indexOf(':') + 2)
                result.append(lineNumber).append(") ").append(splitArr).append("\n")
            }
            lineNumber++
        }
        result.append("\n")
    }

    return result.toString()
}

fun main() {

    val role: ArrayList<String> = arrayListOf()
    val textLine: ArrayList<String> = arrayListOf()

    val file = File("./data/input.txt")
    val bufferedReader = BufferedReader(FileReader(file))
    var line: String
    var isRoles = true

    while (true) {
        line = bufferedReader.readLine() ?: break

        if (line == "roles:") {
            continue
        }

        if (line == "textLines:") {
            isRoles.not().also { isRoles = it }
            continue
        }

        if (isRoles) role.add(line) else textLine.add(line)
    }
    bufferedReader.close()

    val result: String = printTextPerRole(role, textLine)
    println(result)
}