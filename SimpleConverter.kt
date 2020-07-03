package converter

abstract class SimpleConverter : UnitConverter() {

    abstract val unitToIntermediary: Map<String, Double>

    override fun convert(inputMagnitude: Double, inputUnit: String, outputUnit: String): String {
        val toIntermediateFactor = getIntermediateUnitFactor(inputUnit)
        val toOutputFactor = 1 / getIntermediateUnitFactor(outputUnit)
        val outputMagnitude = inputMagnitude * toIntermediateFactor * toOutputFactor

        return print(inputMagnitude, inputUnit) + " is " + print(outputMagnitude, outputUnit)
    }

    override fun print(magnitude: Double, rawUnit: String): String {
        val standardUnit = standardizeUnit(rawUnit)
        return "$magnitude ${if(magnitude == 1.0) toSingular(standardUnit) else standardUnit}"
    }

    // SimpleConverters can convert by simple multiplication
    fun getIntermediateUnitFactor(rawUnit: String): Double {
        val standardUnit = standardizeUnit(rawUnit)
        return unitToIntermediary[standardUnit]!!
    }

    open fun toSingular(pluralUnit: String): String {
        return pluralUnit.dropLast(1)
    }
}