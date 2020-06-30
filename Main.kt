package converter

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    print("Enter a number and a measure of length: ")
    val inputMagnitude = scanner.nextDouble()
    val inputUnit = scanner.next()

    val meterFactor = UnitConverter.getMeterFactor(inputUnit)

    val outputMagnitude = inputMagnitude * meterFactor

    println(
            UnitConverter.print(inputMagnitude, inputUnit) + " is " +
                    UnitConverter.print(outputMagnitude, "meters")
    )


}
