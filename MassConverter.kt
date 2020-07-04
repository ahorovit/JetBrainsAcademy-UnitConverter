package converter

class MassConverter: SimpleConverter() {

    companion object {
        fun hasUnits(inputUnits: String, outputUnits: String): Boolean {
            return hasUnit(inputUnits) && hasUnit(outputUnits)
        }

        fun hasUnit(unit: String): Boolean {
            val instance = MassConverter()
            return instance.inputUnitMap.containsKey(unit)
        }
    }

    override val inputUnitMap: Map<String, String> = mapOf(
            "g" to "grams",
            "gram" to "grams",
            "grams" to "grams",
            "kg" to "kilograms",
            "kilogram" to "kilograms",
            "kilograms" to "kilograms",
            "mg" to "milligrams",
            "milligram" to "milligrams",
            "milligrams" to "milligrams",
            "lb" to "pounds",
            "pound" to "pounds",
            "pounds" to "pounds",
            "oz" to "ounces",
            "ounce" to "ounces",
            "ounces" to "ounces"
    )

    // Intermediary unit is gram
    override val unitToIntermediary: Map<String, Double> = mapOf(
            "grams" to 1.0,
            "kilograms" to 1000.0,
            "milligrams" to 0.001,
            "pounds" to 453.592,
            "ounces" to 28.3495
    )
}