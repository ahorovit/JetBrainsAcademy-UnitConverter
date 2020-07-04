package converter

abstract class UnitConverter {

    companion object {
        fun getConverter(rawInput: String, rawOutput: String): UnitConverter? {
            val inputUnit = rawInput.toLowerCase()
            val outputUnit = rawOutput.toLowerCase()
            var printInput = inputUnit
            var printOutput = outputUnit

            if (!validateUnit(inputUnit)) {
                printInput = "???"
            }

            if (!validateUnit(outputUnit)) {
                printOutput = "???"
            }

            return when {
                DistanceConverter.hasUnits(inputUnit, outputUnit) -> DistanceConverter()
                MassConverter.hasUnits(inputUnit, outputUnit) -> MassConverter()
                TemperatureConverter.hasUnits(inputUnit, outputUnit) -> TemperatureConverter()
                else -> {
                    println("Conversion from $printInput to $printOutput is impossible")
                    return null
                }
            }
        }

        private fun validateUnit(unit: String): Boolean
        {
            return when {
                DistanceConverter.hasUnit(unit) -> true
                MassConverter.hasUnit(unit) -> true
                TemperatureConverter.hasUnit(unit) -> true
                else -> false
            }
        }
    }

    abstract val inputUnitMap: Map<String, String>

    abstract fun convert(inputMagnitude: Double, inputUnit: String, outputUnit: String): String

    abstract fun print(magnitude: Double, rawUnit: String): String

    protected fun standardizeUnit(rawUnit: String) : String {
        return inputUnitMap[rawUnit.toLowerCase()] ?: error("invalid input unit $rawUnit")
    }
}