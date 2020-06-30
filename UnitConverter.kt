package converter

class UnitConverter {

    companion object {
        private val distanceInputs: Map<String, String> = mapOf(
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

        private val distanceToMeters: Map<String, Double> = mapOf(
                "meters" to 1.0,
                "centimeters" to .01,
                "millimeters" to .001,
                "kilometers" to 1000.0,
                "miles" to 1609.35,
                "yards" to 0.9144,
                "feet" to 0.3048,
                "inches" to 0.0253999368683
        )

        private val massToGrams: Map<String, Double> = mapOf(
                "grams" to 1.0,
                "kilograms" to 1000.0,
                "milligrams" to 0.001,
                "pounds" to 453.592,
                "ounces" to 28.3495
        )

        fun getMeterFactor(rawUnit: String): Double {
            val standardUnit = standardizeUnit(rawUnit)
            return distanceToMeters[standardUnit]!!
        }

        fun print(magnitude: Double, rawUnit: String): String {
            val standardUnit = standardizeUnit(rawUnit)
            return "$magnitude ${if(magnitude == 1.0) toSingular(standardUnit) else standardUnit}"
        }

        private fun standardizeUnit(rawUnit: String) : String {
            return distanceInputs[rawUnit.toLowerCase()] ?: error("invalid input unit $rawUnit")
        }

        private fun toSingular(pluralUnit: String): String {
            return when (pluralUnit) {
                "feet" -> "foot"
                "inches" -> "inch"
                else -> pluralUnit.dropLast(1)
            }
        }
    }


//    "lb->kg" to 0.453592


//    °C = (°F - 32)/1.8000
}