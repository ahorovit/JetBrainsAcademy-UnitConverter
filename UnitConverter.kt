package converter

abstract class UnitConverter {

    companion object {
        fun getConverter(rawInput: String, rawOutput: String): UnitConverter? {
            val inputUnit = rawInput.toLowerCase()
            val outputUnit = rawOutput.toLowerCase()
            var printInput = validateUnit(inputUnit)
            var printOutput = validateUnit(outputUnit)

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

        private fun validateUnit(unit: String): String
        {
            return when {
                DistanceConverter.hasUnit(unit) -> DistanceConverter.getStandard(unit)
                MassConverter.hasUnit(unit) -> MassConverter.getStandard(unit)
                TemperatureConverter.hasUnit(unit) -> TemperatureConverter.getStandard(unit)
                else -> "???"
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