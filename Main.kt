package converter

import java.lang.Exception

fun main() {

    val regex = "(-?\\d+\\.?\\d*) (degrees? )?([a-z]+) [a-z]* (degrees? )?([a-z]+)".toRegex(RegexOption.IGNORE_CASE)

    while(true) {
        print("Enter what you want to convert (or exit): ")
        val input = readLine()!!

        if (input == "exit") {
            break
        }

        val matches = regex.find(input)

        if (matches == null) {
            println("Parse error")
            continue
        }

        /*
         * Group values:
         * 0 -> entire string input (if it matches)
         * 1 -> input magnitude
         * 2 -> degree string (optional, ignore)
         * 3 -> input unit
         * 4 -> degree string (optional, ignore)
         * 5 -> output unit
         */
        val inputMagnitude = matches.groupValues[1].toDouble()
        val inputUnit = matches.groupValues[3]
        val outputUnit = matches.groupValues[5]

        val converter = UnitConverter.getConverter(inputUnit, outputUnit) ?: continue

        try {
            println(converter.convert(inputMagnitude, inputUnit, outputUnit))
        } catch (e: Exception) {
            println(e.message)
        }
    }
}
