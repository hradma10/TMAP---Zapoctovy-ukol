package cz.tmap.converterApp.fragmentsAndActivities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import cz.tmap.converterApp.calcAndConvFunctions.Calculate
import cz.tmap.converter.R

class CalculatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expression = view.findViewById<TextView>(R.id.calc_expression)
        val result = view.findViewById<TextView>(R.id.calc_result)
        expression.addTextChangedListener(listener())
        view.findViewById<Button>(R.id.button_zero_calc).setOnClickListener {
            expression.text = "${expression.text}0";
        }
        view.findViewById<Button>(R.id.button_one_calc).setOnClickListener {
            expression.text = "${expression.text}1"
        }
        view.findViewById<Button>(R.id.button_two_calc).setOnClickListener {
            expression.text = "${expression.text}2"
        }
        view.findViewById<Button>(R.id.button_three_calc).setOnClickListener {
            expression.text = "${expression.text}3"
        }
        view.findViewById<Button>(R.id.button_four_calc).setOnClickListener {
            expression.text = "${expression.text}4"
        }
        view.findViewById<Button>(R.id.button_five_calc).setOnClickListener {
            expression.text = "${expression.text}5"
        }
        view.findViewById<Button>(R.id.button_six_calc).setOnClickListener {
            expression.text = "${expression.text}6"
        }
        view.findViewById<Button>(R.id.button_seven_calc).setOnClickListener {
            expression.text = "${expression.text}7"
        }
        view.findViewById<Button>(R.id.button_eight_calc).setOnClickListener {
            expression.text = "${expression.text}8"
        }
        view.findViewById<Button>(R.id.button_nine_calc).setOnClickListener {
            expression.text = "${expression.text}9"
        }
        view.findViewById<Button>(R.id.button_plus_calc).setOnClickListener {
            if (expression.text.isEmpty()) {
                expression.text = "0+"
            } else expression.text = "${expression.text}+"
        }
        view.findViewById<Button>(R.id.button_minus_calc).setOnClickListener {
            if (expression.text.isEmpty()) {
                expression.text = "0-"
            } else expression.text = "${expression.text}-"
        }
        view.findViewById<Button>(R.id.button_division_calc).setOnClickListener {
            if (expression.text.isEmpty()) {
                expression.text = "0/"
            } else expression.text = "${expression.text}/"
        }
        view.findViewById<Button>(R.id.button_multiplication_calc).setOnClickListener {
            if (expression.text.isEmpty()) {
                expression.text = "0*"

            } else expression.text = "${expression.text}*"
        }
        view.findViewById<Button>(R.id.button_dot_calc).setOnClickListener {
            if (expression.text.isEmpty()) {
                expression.text = "0."
            } else expression.text = "${expression.text}."
        }
        view.findViewById<Button>(R.id.button_close_parenthesis_calc).setOnClickListener {
            expression.text = "${expression.text})"
        }
        view.findViewById<Button>(R.id.button_open_parenthesis_calc).setOnClickListener {
            expression.text = "${expression.text}("
        }
        view.findViewById<Button>(R.id.button_clear_calc).setOnClickListener {
            expression.text = ""
            result.text = ""
        }
        view.findViewById<Button>(R.id.button_remove_calc).setOnClickListener {
            var expr = ""
            if (expression.text.isNotEmpty()) {
                expr = expression.text.subSequence(0, expression.text.length - 1).toString()
            }
            expression.text = expr
        }
        view.findViewById<Button>(R.id.button_equals_calc).setOnClickListener {
            val calc = Calculate()
            try {
                var expr = expression.text
                if (expression.text.toString()[0] == '-') expr = "0$expr"
                val res = calc.calculate(addSpaces(expr.toString()))
                expression.text = res
                result.text = ""
            } catch (e: Exception) {
                result.text = getString(R.string.error)
            }
        }
    }


    fun addSpaces(str: String): String {
        val operators = setOf('*', '-', '+', '/')
        var result = ""
        var i = 0
        while (i < str.length) {
            val char = str[i]
            if (char in operators || char == '(' || char == ')') {
                result += " $char "
                i++
            } else if (char == '-' && (i == 0 || str[i - 1] == '(')) {
                val nextChar = if (i + 1 < str.length) str[i + 1] else null
                if ((nextChar != null) && nextChar.isDigit()) {
                    result += "0 - $char".substring(1)
                } else {
                    result += char
                }
                i++
            } else {
                val startIndex = i
                while (i < str.length && str[i] !in operators && str[i] != '(' && str[i] != ')') {
                    i++
                }
                val number = str.substring(startIndex, i)
                result += number
            }
        }
        return result.replace(Regex("\\s+"), " ").trim()
    }


    private fun listener(): TextWatcher {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val expressionTextView: TextView? = view?.findViewById(R.id.calc_expression)
                val resultTextView: TextView? = view?.findViewById(R.id.calc_result)
                var expression = expressionTextView?.text
                val calc = Calculate()
                try {
                    if (expression != null) {
                        if (expression.isEmpty()) if (resultTextView != null) {
                            resultTextView.text = ""
                        }
                    }
                    if (expression.toString()[0] == '-') expression = "0$expression"
                    val result = calc.calculate(addSpaces(expression.toString()))
                    if (resultTextView != null) {
                        resultTextView.text = result
                    }
                } catch (e: Exception) {
                    if (s.toString() == "") if (resultTextView != null) {
                        resultTextView.text = ""
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        return listener
    }
}