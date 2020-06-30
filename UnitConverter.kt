package converter

abstract open class UnitConverter {

    companion object {

        open val unitToIntermediary: Map<String, Double> = mapOf()

        open val inputUnitMap: Map<String, String> = mapOf()



        fun getMeterFactor(rawUnit: String): Double {
            val standardUnit = standardizeUnit(rawUnit)
            return unitToIntermediary[standardUnit]!!
        }

        fun print(magnitude: Double, rawUnit: String): String {
            val standardUnit = standardizeUnit(rawUnit)
            return "$magnitude ${if(magnitude == 1.0) toSingular(standardUnit) else standardUnit}"
        }

        private fun standardizeUnit(rawUnit: String) : String {
            return inputUnitMap[rawUnit.toLowerCase()] ?: error("invalid input unit $rawUnit")
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