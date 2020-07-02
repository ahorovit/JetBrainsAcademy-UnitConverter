package converter

class TemperatureConverter : UnitConverter() {

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
            "kelvin" to "kelvin",
            "kelvins" to "kelvin",
            "k" to "kelvin"
    )

    override fun convert(inputMagnitude: Double, inputUnit: String, outputUnit: String): String {

        // C = (F-32) * 5/9
        // F = C * 9/5 + 32
        // K = C + 273.15
        // C = K -273.15
        // F = K * 9/5 - 459.67
        // K = (F + 459.67) * 5/9

    }

    companion object {
        fun hasUnits(inputUnits: String, outputUnits: String): Boolean {
            val instance = TemperatureConverter()
            return instance.inputUnitMap.containsKey(inputUnits) && instance.inputUnitMap.containsKey(outputUnits)
        }
    }
}