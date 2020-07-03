package converter

abstract class UnitConverter {

    companion object {
        fun getConverter(rawInput: String, rawOutput: String): UnitConverter {
            val inputUnit = rawInput.toLowerCase()
            val outputUnit = rawOutput.toLowerCase()

            return when {
                DistanceConverter.hasUnits(inputUnit, outputUnit) -> DistanceConverter()
                MassConverter.hasUnits(inputUnit, outputUnit) -> MassConverter()
                TemperatureConverter.hasUnits(inputUnit, outputUnit) -> TemperatureConverter()
                else -> error("Unsupported input/output Units: $inputUnit/$outputUnit")
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