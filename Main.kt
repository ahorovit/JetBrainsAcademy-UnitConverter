package converter

fun main() {

    val regex = "(\\d+\\.?\\d*) (degrees? )?([a-z]*) [a-z]* (degrees? )?([a-z]*)".toRegex(RegexOption.IGNORE_CASE)

    do {
        print("Enter what you want to convert (or exit): ")
        val input = readLine()!!
        val matches = regex.find(input)

        if (matches == null) {
            println("Parse error")
            continue
        }

        /*
         * Group values:
         * 0 -> entire string input (if it matches)
         * 1 -> input magnitude
         * 2 -> degree string (ignore)
         * 3 -> input unit
         * 4 -> degree string (ignore)
         * 5 -> output unit
         */
        val inputMagnitude = matches.groupValues[1].toDouble()
        val inputUnit = matches.groupValues[3].toLowerCase()
        val outputUnit = matches.groupValues[5].toLowerCase()

        val converter = UnitConverter.getConverter(inputUnit, outputUnit)
        println(converter.convert(inputMagnitude, inputUnit, outputUnit))

    } while (input != "exit")
}
