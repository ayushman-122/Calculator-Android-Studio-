package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextview: TextView
    private lateinit var previousCalculationTextview: TextView

    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultTextview = findViewById(R.id.screen)
        previousCalculationTextview = findViewById(R.id.opdetails)

        val btn0: Button = findViewById<Button>(R.id.num0)
        val btn1: Button = findViewById<Button>(R.id.num1)
        val btn2: Button = findViewById<Button>(R.id.num2)
        val btn3: Button = findViewById<Button>(R.id.num3)
        val btn4: Button = findViewById<Button>(R.id.num4)
        val btn5: Button = findViewById<Button>(R.id.num5)
        val btn6: Button = findViewById<Button>(R.id.num6)
        val btn7: Button = findViewById<Button>(R.id.num7)
        val btn8: Button = findViewById<Button>(R.id.num8)
        val btn9: Button = findViewById<Button>(R.id.num9)

        val clr: Button = findViewById<Button>(R.id.btnclr)
        val del: Button = findViewById<Button>(R.id.backspace)
        val dot: Button = findViewById<Button>(R.id.decimal)
        val equals: Button = findViewById<Button>(R.id.equals)

        val percent: Button = findViewById<Button>(R.id.percent)
        val divide: Button = findViewById<Button>(R.id.divide)
        val multiply: Button = findViewById<Button>(R.id.mul)
        val subtract: Button = findViewById<Button>(R.id.minus)
        val add: Button = findViewById<Button>(R.id.plus)

        btn0.setOnClickListener { appendnumber("0") }
        btn1.setOnClickListener { appendnumber("1") }
        btn2.setOnClickListener { appendnumber("2") }
        btn3.setOnClickListener { appendnumber("3") }
        btn4.setOnClickListener { appendnumber("4") }
        btn5.setOnClickListener { appendnumber("5") }
        btn6.setOnClickListener { appendnumber("6") }
        btn7.setOnClickListener { appendnumber("7") }
        btn8.setOnClickListener { appendnumber("8") }
        btn9.setOnClickListener { appendnumber("9") }

        clr.setOnClickListener { clearcalc() }
        del.setOnClickListener { delval() }
        dot.setOnClickListener { appendnumber(".") }
        equals.setOnClickListener { calculateResult() }

        percent.setOnClickListener { opcode("%") }
        divide.setOnClickListener { opcode("÷") }
        multiply.setOnClickListener { opcode("×") }
        subtract.setOnClickListener { opcode("-") }
        add.setOnClickListener { opcode("+") }
    }

    private fun appendnumber(number: String){
        if(isNewOperation){
            resultTextview.text = number
            isNewOperation = false
        }else{
            resultTextview.text = "${resultTextview.text}$number"
        }
    }

    private fun opcode(op: String){
        firstNumber = resultTextview.text.toString().toDouble()
        operation = op
        isNewOperation = true
        previousCalculationTextview.text = "$firstNumber $op"
    }

    private fun calculateResult(){
        try{
            val secondNumber = resultTextview.text.toString().toDouble()
            val res: Double = when(operation){
                "+" -> firstNumber+secondNumber
                "-" -> firstNumber-secondNumber
                "×" -> firstNumber*secondNumber
                "÷" -> firstNumber/secondNumber
                "%" -> firstNumber*secondNumber/100
                else -> secondNumber
            }

            previousCalculationTextview.text = "$firstNumber $operation $secondNumber ="
            resultTextview.text = res.toString()
            isNewOperation = true

        }catch (e: Exception){
            resultTextview.text = "ERROR!!"
        }
    }

    private fun clearcalc(){
        previousCalculationTextview.text = ""
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
        resultTextview.text = "0"
    }

    private fun delval(){
        if(resultTextview.text.length>1 && resultTextview.text!="Error" && resultTextview.text!="Infinity"){
            resultTextview.text = resultTextview.text.dropLast(1)
        }else{
            clearcalc()
        }
    }
}