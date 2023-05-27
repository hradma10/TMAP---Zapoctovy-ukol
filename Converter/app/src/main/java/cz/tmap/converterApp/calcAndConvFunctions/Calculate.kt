package cz.tmap.converterApp.calcAndConvFunctions

import java.math.BigDecimal
import java.math.RoundingMode

class Calculate {

    private fun copyAndInsert(
        characters: Array<String?>,
        indexOfChar: Int,
        result: Double
    ): Array<String?> {
        val temp = arrayOfNulls<String>(characters.size - 2)
        var indexTemp = 0
        for (i in characters.indices) {
            if (i < indexOfChar - 1 || indexOfChar + 1 < i) {
                temp[indexTemp] = characters[i]
                indexTemp++
            } else if (i == indexOfChar - 1) {
                temp[indexTemp] = result.toString()
                indexTemp++
            }
        }
        return temp
    }

    private fun calculateParenthesis(characters: Array<String?>): Array<String?> {
        var charactersArr = characters
        var depth = 0
        var startIndex: Int
        var endIndex: Int
        var endSearch = false
        while (!endSearch) {
            startIndex = -1
            endIndex = -1
            for (i in charactersArr.indices) {
                val character = charactersArr[i]
                if (character == "(") {
                    depth++
                    if (depth == 1) startIndex = i
                } else if (character == ")") {
                    if (depth > 0) {
                        depth--
                        if (depth == 0) {
                            endIndex = i
                            break
                        }
                    } else {
                        throw IllegalArgumentException()
                    }
                }
                if (i == charactersArr.size - 1) {
                    endSearch = true
                }
            }
            require(depth <= 0)
            if (startIndex != -1 && endIndex != -1) {
                val parenthesis: Array<String?> =
                    charactersArr.copyOfRange(startIndex + 1, endIndex)
                val string = StringBuilder()
                for (c in parenthesis) {
                    string.append(c)
                    string.append(" ")
                }
                val resultOfParenthesis = calculate(string.toString())
                val temp = arrayOfNulls<String>(charactersArr.size - (endIndex - startIndex))
                var indexTemp = 0
                for (i in charactersArr.indices) {
                    val c = charactersArr[i]
                    if (i in startIndex..endIndex) {
                        if (i == startIndex) {
                            temp[indexTemp] = resultOfParenthesis
                            indexTemp++
                        }
                    } else {
                        temp[indexTemp] = c
                        indexTemp++
                    }
                }
                charactersArr = temp
            }
        }
        return charactersArr
    }

    private fun calculateMultAndDiv(characters: Array<String?>): Array<String?> {
        var charactersArr = characters
        var didComp: Boolean
        var right: Double
        var left: Double
        var endCalc = false
        while (!endCalc) {
            didComp = false
            for (i in charactersArr.indices) {
                val character = charactersArr[i]
                if (character == "*" || character == "/") {
                    right = charactersArr[i + 1]!!.toDouble()
                    left = charactersArr[i - 1]!!.toDouble()
                    val result: Double = if (character == "*") {
                        left * right
                    } else left / right
                    charactersArr = copyAndInsert(charactersArr, i, result)
                    didComp = true
                }
                if (didComp) {
                    break
                } else if (i == charactersArr.size - 1) {
                    endCalc = true
                }
            }
        }
        return charactersArr
    }

    private fun calculateAdditAndSubtr(characters: Array<String?>): Array<String?> {
        var charactersArr = characters
        var didComp: Boolean
        var right: Double
        var left: Double
        var endCalc = false
        while (!endCalc) {
            didComp = false
            for (i in charactersArr.indices) {
                val character = charactersArr[i]
                if (character == "+" || character == "-") {
                    right = charactersArr[i + 1]!!.toDouble()
                    left = charactersArr[i - 1]!!.toDouble()
                    val result: Double = if (character == "+") {
                        left + right
                    } else left - right
                    charactersArr = copyAndInsert(charactersArr, i, result)
                    didComp = true
                }
                if (didComp) {
                    break
                } else if (i == charactersArr.size - 1) {
                    endCalc = true
                }
            }
        }
        return charactersArr
    }

    private fun format(result: Double): String {
        var number = BigDecimal(result)
        number = number.setScale(9, RoundingMode.HALF_UP)
        number = number.stripTrailingZeros()
        return number.toPlainString()
    }


    fun calculate(input: String): String {
        var characters: Array<String?> =
            input.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (input.contains("(") || input.contains(")")) characters =
            calculateParenthesis(characters)
        if (input.contains("*") || input.contains("/")) characters =
            calculateMultAndDiv(characters)
        if (input.contains("+") || input.contains("-")) characters =
            calculateAdditAndSubtr(characters)
        return format(characters[0]!!.toDouble())
    }
}
