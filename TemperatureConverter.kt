package converter

class TemperatureConverter : UnitConverter() {

    companion object {
        fun hasUnits(inputUnits: String, outputUnits: String): Boolean {
            return hasUnit(inputUnits) && hasUnit(outputUnits)
        }

        fun hasUnit(unit: String): Boolean {
            val instance = TemperatureConverter()
            return instance.inputUnitMap.containsKey(unit)
        }

        fun getStandard(unit: String): String {
            val instance = TemperatureConverter()
            return instance.inputUnitMap[unit]!!
        }
    }

    override val inputUnitMap: Map<String, String> = mapOf(
            "celsius" to "degrees Celsius",
            "degree celsius" to "degrees Celsius",
            "degrees celsius" to "degrees Celsius",
            "dc" to "degrees Celsius",
            "c" to "degrees Celsius",
            "fahrenheit" to "degrees Fahrenheit",
            "degree fahrenheit" to "degrees Fahrenheit",
            "degrees fahrenheit" to "degrees Fahrenheit",
            "df" to "degrees Fahrenheit",
            "f" to "degrees Fahrenheit",
            "kelvin" to "kelvins",
            "kelvins" to "kelvins",
            "k" to "kelvins"
    )

    override fun convert(inputMagnitude: Double, inputUnit: String, outputUnit: String): String {
        val inputStandard = standardizeUnit(inputUnit)
        val outputStandard = standardizeUnit(outputUnit)

        val outputMagnitude: Double = when {
            inputStandard == "degrees Fahrenheit" && outputStandard == "degrees Celsius" -> {
                // C = (F-32) * 5/9
                (inputMagnitude - 32.0) * (5.0 / 9.0)
            }
            inputStandard == "degrees Celsius" && outputStandard == "degrees Fahrenheit" -> {
                // F = C * 9/5 + 32
                inputMagnitude * (9.0 / 5.0) + 32
            }
            inputStandard == "kelvins" && outputStandard == "degrees Fahrenheit" -> {
                // F = K * 9/5 - 459.67
                inputMagnitude * (9.0 / 5.0) - 459.67
            }
            inputStandard == "degrees Fahrenheit" && outputStandard == "kelvins" -> {
                // K = (F + 459.67) * 5/9
                (inputMagnitude + 459.67) * (5.0 / 9.0)
            }
            inputStandard == "kelvins" && outputStandard == "degrees Celsius" -> {
                // C = K -273.15
                inputMagnitude - 273.15
            }
            inputStandard == "degrees Celsius" && outputStandard == "kelvins" -> {
                // K = C + 273.15
                inputMagnitude + 273.15
            }
            else -> error("Invalid inputs")
        }

        return print(inputMagnitude, inputUnit) + " is " + print(outputMagnitude, outputStandard)
    }

    override fun print(magnitude: Double, rawUnit: String): String {
        var standardUnit = standardizeUnit(rawUnit)
        val degreeString: String

        if (standardUnit == "kelvins") {
            if (magnitude == 1.0) {
                standardUnit = standardUnit.dropLast(1)
            }

            degreeString = ""
        } else {
            degreeString = "degree${if (magnitude != 1.0) "s" else ""}"
        }

        return "$magnitude $degreeString $standardUnit"
    }
}