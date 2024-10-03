package edu.tcu.dotnguyen.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {
    private var afterOperator: Boolean = false
    private var firstDigitZero: Boolean = true
    private var hasDot: Boolean = false
//    private var startIndex = 0
//    private var divideZero: Boolean = false
    private val inputList: MutableList<Any> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Result from TextView
        val resultView = findViewById<TextView>(R.id.result_tv)
        
        // Clear Button
        findViewById<Button>(R.id.clear_btn).setOnClickListener { onClear(resultView) }
        // Equal Button
        findViewById<Button>(R.id.equal_btn).setOnClickListener { onEqual(resultView) }

        // Divide Button
        findViewById<Button>(R.id.divide_btn).setOnClickListener { onOperator(resultView,"/") }

        // Multiply Button
        findViewById<Button>(R.id.multiply_btn).setOnClickListener { onOperator(resultView,"*") }

        // Subtract Button
        findViewById<Button>(R.id.subtract_btn).setOnClickListener { onOperator(resultView,"-") }

        // Add Button
        findViewById<Button>(R.id.add_btn).setOnClickListener { onOperator(resultView,"+") }
        // Dot Button
        findViewById<Button>(R.id.dot_btn).setOnClickListener { onDot(resultView) }

        // Number 0 Button
        findViewById<Button>(R.id.zero_btn).setOnClickListener { onDigit(resultView, "0") }

        // Number 1 Button
        findViewById<Button>(R.id.one_btn).setOnClickListener { onDigit(resultView, "1") }

        // Number 2 Button
        findViewById<Button>(R.id.two_btn).setOnClickListener { onDigit(resultView, "2") }

        // Number 3 Button
        findViewById<Button>(R.id.three_btn).setOnClickListener { onDigit(resultView, "3") }

        // Number 4 Button
        findViewById<Button>(R.id.four_btn).setOnClickListener { onDigit(resultView, "4") }

        // Number 5 Button
        findViewById<Button>(R.id.five_btn).setOnClickListener { onDigit(resultView, "5") }

        // Number 6 Button
        findViewById<Button>(R.id.six_btn).setOnClickListener { onDigit(resultView, "6") }

        // Number 7 Button
        findViewById<Button>(R.id.seven_btn).setOnClickListener { onDigit(resultView, "7") }

        // Number 8 Button
        findViewById<Button>(R.id.eight_btn).setOnClickListener { onDigit(resultView, "8") }

        // Number 9 Button
        findViewById<Button>(R.id.nine_btn).setOnClickListener { onDigit(resultView, "9") }

    }

    private fun onClear(resultTv: TextView) {
        inputList.clear()
        inputList.add("0")
        resultTv.text = "0"
        afterOperator = false
        firstDigitZero = true
        hasDot = false
    }

    private fun onEqual(resultTv: TextView) {
        if (inputList.isEmpty() || inputList.last() in listOf("+", "-", "*", "/")) {
            resultTv.text = getString(R.string.error_msg)
            return
        }

        val operators = mutableListOf<String>()
        val numbers = mutableListOf<BigDecimal>()
        var currentNumber = ""

        for (item in inputList) {
            if (item in listOf("+", "-", "*", "/")) {
                numbers.add(BigDecimal(currentNumber))
                operators.add(item as String)
                currentNumber = ""
            } else {
                currentNumber += item.toString()
            }
        }
        numbers.add(BigDecimal(currentNumber))

        // Process * and / first (left to right)
        var i = 0
        while (i < operators.size) {
            if (operators[i] == "*" || operators[i] == "/") {
                val left = numbers[i]
                val right = numbers[i + 1]
                val result = if (operators[i] == "*") {
                    left.multiply(right)
                } else {
                    if (right.compareTo(BigDecimal.ZERO) == 0) {
                        resultTv.text = getString(R.string.error_msg)
                        return
                    } else {
                        left.divide(right, 10, RoundingMode.HALF_UP) // You can adjust precision as needed
                    }
                }
                numbers[i] = result
                numbers.removeAt(i + 1)
                operators.removeAt(i)
            } else {
                i++
            }
        }

        // Process + and - (left to right)
        i = 0
        while (i < operators.size) {
            val left = numbers[i]
            val right = numbers[i + 1]
            val result = if (operators[i] == "+") {
                left.add(right)
            } else {
                left.subtract(right)
            }
            numbers[i] = result
            numbers.removeAt(i + 1)
            operators.removeAt(i)
        }

        // Format the result to remove redundant zeros using BigDecimal.stripTrailingZeros()
        val finalResult = numbers[0].stripTrailingZeros()
        val formattedResult = if (finalResult.scale() <= 0) {
            finalResult.toBigInteger().toString()  // Convert to integer if it's a whole number
        } else {
            finalResult.toPlainString()  // Keep decimal without scientific notation
        }

        resultTv.text = formattedResult
        inputList.clear()
        inputList.add(formattedResult)
    }


    private fun onDot(resultTv: TextView) {
        val lastElement = inputList.lastOrNull()

        if (lastElement == null || lastElement in listOf("+", "-", "*", "/")) {
            // If the last element is an operator or the list is empty, append "0." after the operator
            inputList.add("0.")
        } else if (lastElement is String && lastElement.matches(Regex("\\d+")) && !lastElement.contains(".")) {
            // If the last element is a number and does not contain a dot, add the dot
            inputList[inputList.size - 1] = "$lastElement."
        }

        resultTv.text = inputList.joinToString("")
        hasDot = true
    }

    private fun onDigit(resultTv: TextView, digit: String) {
        if (inputList.isEmpty()) {
            inputList.add(digit)
        } else {
            val lastElement = inputList.last()
            if (lastElement in listOf("+", "-", "*", "/")) {
                inputList.add(digit)
            } else {
                if (lastElement == "0" && digit == "0") return
                else if (lastElement == "0" && digit != "0") inputList[inputList.size - 1] = digit
                else inputList[inputList.size - 1] = lastElement.toString() + digit
            }
        }
        resultTv.text = inputList.joinToString("")
    }

    private fun onOperator(resultTv: TextView, operator: String) {
        if (inputList.isNotEmpty() && inputList.last() in listOf("+", "-", "*", "/")) {
            inputList[inputList.size - 1] = operator
        } else {
            inputList.add(operator)
        }
        resultTv.text = inputList.joinToString("")
    }

}