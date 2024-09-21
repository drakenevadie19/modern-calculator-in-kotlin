package edu.tcu.dotnguyen.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var afterOperator: Boolean = false
    var previousZero: Boolean = true
    var hasDot: Boolean = false
    var startIndex = 0
    var toDisplayExpression: String = "0";

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
        findViewById<Button>(R.id.clear_btn).setOnClickListener { onClear() }
        // Equal Button
        findViewById<Button>(R.id.equal_btn).setOnClickListener { onEqual() }

        // Divide Button
        findViewById<Button>(R.id.divide_btn).setOnClickListener { onOperator("/") }

        // Multiply Button
        findViewById<Button>(R.id.multiply_btn).setOnClickListener { onOperator("*") }

        // Subtract Button
        findViewById<Button>(R.id.subtract_btn).setOnClickListener { onOperator("-") }

        // Add Button
        findViewById<Button>(R.id.add_btn).setOnClickListener { onOperator("+") }
        // Dot Button
        findViewById<Button>(R.id.dot_btn).setOnClickListener { onDot() }

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

    private fun onClear() {

    }

    private fun onEqual() {
        print('.')
    }

    private fun onDot() {
        print('=')
    }

    private fun onDigit(resultTv: TextView, digit: String) {
        // for each index input, add to textView
        // If inputted digit = 0 => consider the firstZero var => true =
        if (digit != "0") {
            toDisplayExpression +=  "0"
        } else {
            var currentExpression = toDisplayExpression.toCharArray()
            if (currentExpression.size == 1) {
                if (currentExpression[0] == '0') {
                    toDisplayExpression = digit;
                } else {

                }
            }
            toDisplayExpression +=  digit
        }

        resultTv.text = toDisplayExpression
        print(digit)
    }

    private fun onOperator(operator: String) {
        print(operator)
    }

}