package edu.tcu.dotnguyen.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
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
        var resultView = findViewById<TextView>(R.id.result_tv)
        
        // Clear Button
        var buttonClear = findViewById<Button>(R.id.clear_btn)
        // Equal Button
        var buttonEqual = findViewById<Button>(R.id.equal_btn)
        // Divide Button
        var buttonDivide = findViewById<Button>(R.id.divide_btn)
        // Multiply Button
        var buttonMultiple = findViewById<Button>(R.id.multiply_btn)
        // Subtract Button
        var buttonSubtract = findViewById<Button>(R.id.subtract_btn)
        // Add Button
        var buttonAdd = findViewById<Button>(R.id.add_btn)
        // Dot Button
        var buttonDot = findViewById<Button>(R.id.dot_btn)

        // Number 0 Button
        var button0 = findViewById<Button>(R.id.zero_btn)
        // Number 1 Button
        var button1 = findViewById<Button>(R.id.one_btn)
        // Number 2 Button
        var button2 = findViewById<Button>(R.id.two_btn)
        // Number 3 Button
        var button3 = findViewById<Button>(R.id.three_btn)
        // Number 4 Button
        var button4 = findViewById<Button>(R.id.four_btn)
        // Number 5 Button
        var button5 = findViewById<Button>(R.id.five_btn)
        // Number 6 Button
        var button6 = findViewById<Button>(R.id.six_btn)
        // Number 7 Button
        var button7 = findViewById<Button>(R.id.seven_btn)
        // Number 8 Button
        var button8 = findViewById<Button>(R.id.eight_btn)
        // Number 9 Button
        var button9 = findViewById<Button>(R.id.nine_btn)


    }
}