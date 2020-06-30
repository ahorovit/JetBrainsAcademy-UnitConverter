package converter

class MassConverter: UnitConverter() {

    val unitToIntermediary: Map<String, Double> = mapOf(
            "grams" to 1.0,
            "kilograms" to 1000.0,
            "milligrams" to 0.001,
            "pounds" to 453.592,
            "ounces" to 28.3495
    )
}