package converter

class DistanceConverter : SimpleConverter() {

    companion object {
        fun hasUnits(inputUnits: String, outputUnits: String): Boolean {
            val instance = DistanceConverter()
            return instance.inputUnitMap.containsKey(inputUnits) && instance.inputUnitMap.containsKey(outputUnits)
        }
    }

    override val inputUnitMap: Map<String, String> = mapOf(
            "m" to "meters",
            "meter" to "meters",
            "meters" to "meters",
            "km" to "kilometers",
            "kilometer" to "kilometers",
            "kilometers" to "kilometers",
            "cm" to "centimeters",
            "centimeter" to "centimeters",
            "centimeters" to "centimeters",
            "mm" to "millimeters",
            "millimeter" to "millimeters",
            "millimeters" to "millimeters",
            "mi" to "miles",
            "mile" to "miles",
            "miles" to "miles",
            "yd" to "yards",
            "yard" to "yards",
            "yards" to "yards",
            "ft" to "feet",
            "foot" to "feet",
            "feet" to "feet",
            "in" to "inches",
            "inch" to "inches",
            "inches" to "inches"
    )

    // Intermediary unit is meter
    override val unitToIntermediary: Map<String, Double> = mapOf(
            "meters" to 1.0,
            "centimeters" to .01,
            "millimeters" to .001,
            "kilometers" to 1000.0,
            "miles" to 1609.35,
            "yards" to 0.9144,
            "feet" to 0.3048,
            "inches" to 0.0253999368683
    )

    override fun toSingular(pluralUnit: String): String {
        return when (pluralUnit) {
            "feet" -> "foot"
            "inches" -> "inch"
            else -> pluralUnit.dropLast(1)
        }
    }
}