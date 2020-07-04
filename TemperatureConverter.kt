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
    }

    override val inputUnitMap: Map<String, String> = mapOf(
            "celsius" to "celsius",
            "degree celsius" to "celsius",
            "degrees celsius" to "celsius",
            "dc" to "celsius",
            "c" to "celsius",
            "fahrenheit" to "fahrenheit",
            "degree fahrenheit" to "fahrenheit",
            "degrees fahrenheit" to "fahrenheit",
            "df" to "fahrenheit",
            "f" to "fahrenheit",
            "kelvin" to "kelvins",
            "kelvins" to "kelvins",
            "k" to "kelvins"
    )

    override fun convert(inputMagnitude: Double, inputUnit: String, outputUnit: String): String {
        val inputStandard = standardizeUnit(inputUnit)
        val outputStandard = standardizeUnit(outputUnit)

        val outputMagnitude: Double = when {
            inputStandard == "fahrenheit" && outputStandard == "celsius" -> {
                // C = (F-32) * 5/9
                (inputMagnitude - 32.0) * (5.0 / 9.0)
            }
            inputStandard == "celsius" && outputStandard == "fahrenheit" -> {
                // F = C * 9/5 + 32
                inputMagnitude * (9.0 / 5.0) + 32
            }
            inputStandard == "kelvins" && outputStandard == "fahrenheit" -> {
                // F = K * 9/5 - 459.67
                inputMagnitude * (9.0 / 5.0) - 459.67
            }
            inputStandard == "fahrenheit" && outputStandard == "kelvins" -> {
                // K = (F + 459.67) * 5/9
                (inputMagnitude + 459.67) * (5.0 / 9.0)
            }
            inputStandard == "kelvins" && outputStandard == "celsius" -> {
                // C = K -273.15
                inputMagnitude - 273.15
            }
            inputStandard == "celsius" && outputStandard == "kelvins" -> {
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
            standardUnit = standardUnit.capitalize()
        }

        return "$magnitude $degreeString $standardUnit"
    }
}