package edu.tcu.dotnguyen.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var afterOperator: Boolean = false
    private var firstDigitZero: Boolean = true
    private var hasDot: Boolean = false
    private var startIndex = 0
    private var divideZero: Boolean = false
    private val inputList: MutableList<Any> = mutableListOf()
    private var currentElement = "0"

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
        findViewById<Button>(R.id.equal_btn).setOnClickListener { onEqual() }

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
        // Clear everything
        inputList.clear()
        currentElement = "0" // Reset currentElement to 0
        resultTv.text = currentElement // Reset the display to 0
        afterOperator = false
        firstDigitZero = true
        hasDot = false
    }

    private fun onEqual() {
        print('.')
    }

    private fun onDot(resultTv: TextView) {
////        var currentText = resultTv.text.toString()
//
//        // Only add a dot if there isn't one in the current number segment
//        if (!hasDot) {
//            inputList.add(".") // Add dot to inputList
//            hasDot = true // Mark that a dot has been added
//            resultTv.text = inputList.joinToString("") // Update the display
//        }
        // Only allow one dot per number
        if (!currentElement.contains(".")) {
            currentElement += "." // Append the dot to the current number
        }
        // Update the result text
        resultTv.text = inputList.joinToString("") + currentElement

    }

    private fun onDigit(resultTv: TextView, digit: String) {
//        val currentText = resultTv.text.toString() // Get the current displayed text
//
//        // Handle the case after an operator is clicked
//        if (afterOperator) {
//            // If an operator was clicked, reset the flag and replace the display with the new digit
//            afterOperator = false
//            if (digit != "0") {
//                inputList.add(digit) // Add non-zero digit to inputList
//            } else {
//                inputList.add("0") // Add zero to inputList
//            }
//        } else {
//            if (currentText == "0" && digit == "0") {
//                // If current text is 0 and user clicks 0, do nothing
//                return
//            } else if (currentText == "0" && digit != "0") {
//                // Replace 0 with the new digit in inputList
//                inputList.clear() // Clear the inputList since we are replacing the initial 0
//                inputList.add(digit)
//            } else if (currentText.endsWith("0") && firstDigitZero) {
//                // Handle the case for "1+0", replace the last zero if a new digit is clicked
//                if (digit != "0") {
//                    inputList[inputList.size - 1] = digit // Replace the last zero in the inputList
//                    firstDigitZero = false // Reset the firstDigitZero flag
//                }
//            } else {
//                inputList.add(digit) // Normal case, just append the digit to inputList
//            }
//
//        }
//
//        // Join inputList to form the current expression and update the TextView
//        resultTv.text = inputList.joinToString("")
        // If current element is an operator, add the operator to inputList and reset currentElement
        if (currentElement in listOf("+", "-", "*", "/")) {
            inputList.add(currentElement) // Add the operator to inputList
            currentElement = digit // Start a new number
        } else {
            // Handle the case of replacing "0" with the new digit
            if (currentElement == "0") {
                currentElement = digit // Replace the 0
            } else {
                currentElement += digit // Append the digit to the current number
            }
        }
        // Update the result text with the joined inputList and currentElement
        resultTv.text = inputList.joinToString("") + currentElement
    }

    private fun onOperator(resultTv: TextView, operator: String) {
//        // Check if the last element is an operator, don't add consecutive operators
//        if (inputList.isNotEmpty() && inputList.last() in listOf("+", "-", "*", "/")) {
//            return // Do nothing if an operator is already the last element
//        }
//        inputList.add(operator) // Add the operator to inputList
//        afterOperator = true // Set the flag so next input replaces the current digit
//        firstDigitZero = true // Reset firstDigitZero in case next digit is 0
        // Check if the last input is an operator, if so, do nothing
        if (currentElement in listOf("+", "-", "*", "/")) {
            return // If the last input was an operator, ignore the new operator
        }

        // Save the current number to inputList before adding the operator
        inputList.add(currentElement) // Add the current number to inputList
        currentElement = operator // Set the operator as the new current element

        // Update the result text with the joined inputList and currentElement
        resultTv.text = inputList.joinToString("") + currentElement
    }

}