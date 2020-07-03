package converter

fun main() {
    do {
        print("Enter what you want to convert (or exit): ")
        val input = readLine()!!.split(" ")

        if(input.size == 4) {
            val inputMagnitude = input[0].toDouble()
            val inputUnit = input[1].toLowerCase()
            val outputUnit = input[3].toLowerCase()
            val converter = UnitConverter.getConverter(inputUnit, outputUnit)

            println(converter.convert(inputMagnitude, inputUnit, outputUnit))
        }
    } while(input[0] != "exit")
}
