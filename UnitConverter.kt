package converter

abstract class UnitConverter {

    companion object {
        fun getConverter(rawInput: String, rawOutput: String): UnitConverter {
            val inputUnit = rawInput.toLowerCase()
            val outputUnit = rawOutput.toLowerCase()

            return when {
                DistanceConverter.hasUnits(inputUnit, outputUnit) -> DistanceConverter()
                MassConverter.hasUnits(inputUnit, outputUnit) -> MassConverter()
                else -> error("Unsupported input/output Units: $inputUnit/$outputUnit")
            }
        }
    }

    abstract val unitToIntermediary: Map<String, Double>

    abstract val inputUnitMap: Map<String, String>

    fun convert(inputMagnitude: Double, inputUnit: String, outputUnit: String): String {
        val toIntermediateFactor = getIntermediateUnitFactor(inputUnit)
        val toOutputFactor = 1 / getIntermediateUnitFactor(outputUnit)
        val outputMagnitude = inputMagnitude * toIntermediateFactor * toOutputFactor

        return print(inputMagnitude, inputUnit) + " is " + print(outputMagnitude, outputUnit)
    }

    fun getIntermediateUnitFactor(rawUnit: String): Double {
        val standardUnit = standardizeUnit(rawUnit)
        return unitToIntermediary[standardUnit]!!
    }

    private fun standardizeUnit(rawUnit: String) : String {
        return inputUnitMap[rawUnit.toLowerCase()] ?: error("invalid input unit $rawUnit")
    }

    fun print(magnitude: Double, rawUnit: String): String {
        val standardUnit = standardizeUnit(rawUnit)
        return "$magnitude ${if(magnitude == 1.0) toSingular(standardUnit) else standardUnit}"
    }

    open fun toSingular(pluralUnit: String): String {
        return pluralUnit.dropLast(1)
    }
}