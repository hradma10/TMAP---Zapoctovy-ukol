package cz.tmap.converterApp.calcAndConvFunctions

class Unit {
    private val name: String
    val factor: Double

    constructor(name: String, factor: Double) {
        this.name = name
        this.factor = factor
    }

    constructor(name: String) {
        this.name = name
        factor = 0.0
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other.javaClass != this.javaClass) {
            return false
        }
        val other = other as Unit
        return name == other.name
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + factor.hashCode()
        return result
    }
}
