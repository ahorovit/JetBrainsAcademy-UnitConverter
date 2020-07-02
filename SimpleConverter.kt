package converter

abstract class SimpleConverter : UnitConverter() {

    abstract val unitToIntermediary: Map<String, Double>

    override fun convert(inputMagnitude: Double, inputUnit: String, outputUnit: String): String {
        val toIntermediateFactor = getIntermediateUnitFactor(inputUnit)
        val toOutputFactor = 1 / getIntermediateUnitFactor(outputUnit)
        val outputMagnitude = inputMagnitude * toIntermediateFactor * toOutputFactor

        return print(inputMagnitude, inputUnit) + " is " + print(outputMagnitude, outputUnit)
    }

    // SimpleConverters can convert by simple multiplication
    fun getIntermediateUnitFactor(rawUnit: String): Double {
        val standardUnit = standardizeUnit(rawUnit)
        return unitToIntermediary[standardUnit]!!
    }
}