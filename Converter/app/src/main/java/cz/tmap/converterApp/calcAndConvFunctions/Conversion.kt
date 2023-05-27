package cz.tmap.converterApp.calcAndConvFunctions

import java.math.BigDecimal

class Conversion {
    private val units = Units()
    private fun format(result: Double): String {
        val formattedNumber = String.format("%.8g", result).replace(",", ".")
        var number = BigDecimal(formattedNumber)
        number = number.stripTrailingZeros()
        return if (formattedNumber.length - formattedNumber.indexOf(".") - 1 <= 9) {
            number.toPlainString()
        } else number.toString()
    }

    private fun convert(units: Array<Unit>, input: String, from: String, to: String): String {
        if (from == to) format(input.toDouble())
        val indexFrom = listOf(*units).indexOf(Unit(from))
        val indexTo = listOf(*units).indexOf(Unit(to))
        val result = input.toDouble() * units[indexFrom].factor / units[indexTo].factor
        return format(result)
    }

    fun area(input: String, from: String, to: String): String {
        return convert(units.areaUnits, input, from, to)
    }

    fun data(input: String, from: String, to: String): String {
        return convert(units.dataUnits, input, from, to)
    }

    fun length(input: String, from: String, to: String): String {
        return convert(units.lengthUnits, input, from, to)
    }

    fun mass(input: String, from: String, to: String): String {
        return convert(units.massUnits, input, from, to)
    }

    fun energy(input: String, from: String, to: String): String {
        return convert(units.energyUnits, input, from, to)
    }

    fun speed(input: String, from: String, to: String): String {
        return convert(units.speedUnits, input, from, to)
    }

    fun temperature(input: String, from: String, to: String): String {
        if (from == to) return input
        var input = input
        var from = from
        if (from == "°F") {
            input = ((input.toDouble() - 32) * 5 / 9).toString()
            from = "°C"
        }
        val output: Double = if (from == "°C") {
            if (to == "°K") {
                (input.toDouble() + 273.15)
            } else ((input.toDouble() * 9 / 5) + 32)
        } else {
            if (to == "°F") {
                ((input.toDouble() - 273.15) * 9 / 5 + 32)
            } else {
                (input.toDouble() - 273.15)
            }
        }
        return output.toString()
    }

    fun time(input: String, from: String, to: String): String {
        return convert(units.timeUnits, input, from, to)
    }

    fun power(input: String, from: String, to: String): String {
        return convert(units.powerUnits, input, from, to)
    }

    fun pressure(input: String, from: String, to: String): String {
        return convert(units.pressureUnits, input, from, to)
    }

    fun frequency(input: String, from: String, to: String): String {
        return convert(units.frequencyUnits, input, from, to)
    }

    fun angle(input: String, from: String, to: String): String {
        return convert(units.angleUnits, input, from, to)
    }

    fun density(input: String, from: String, to: String): String {
        return convert(units.densityUnits, input, from, to)
    }

    fun volume(input: String, from: String, to: String): String {
        return convert(units.volumeUnits, input, from, to)
    }

    fun dataTransfer(input: String, from: String, to: String): String {
        return convert(units.dataTransferUnits, input, from, to)
    }
}
