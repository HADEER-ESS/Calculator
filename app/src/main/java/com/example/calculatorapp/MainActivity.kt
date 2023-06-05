package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var inputText :TextView? = null
    private var clearBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputText = findViewById(R.id.textView)
        clearBtn = findViewById(R.id.buttonNumClear)
    }

    fun onNumberClick(view : View){
        inputText?.append((view as Button).text)
    }

    fun onEqualClicked(view : View){
        var textValue = inputText?.text?.toString()
        var prefix : String = ""
        if(textValue?.startsWith("-") == true){
            prefix = "-"
            textValue = textValue.substring(1)
        }
        try {
            if(textValue?.contains("-") == true){
                //create Operation on a negative value
                val splittedValue = textValue?.split("-")
                var firstNumber = splittedValue?.get(0)
                var secondNumber = splittedValue?.get(1)
                if(prefix === "-"){
                    firstNumber = "-$firstNumber"
                }
                val result = firstNumber?.toDouble()?.minus(secondNumber?.toDouble()!!)
                inputText?.text = result.toString()
            }
            else if(textValue?.contains("+") == true){
                val splittedValue = textValue?.split("+")
                var firstNumber = splittedValue?.get(0)
                var secondNumber = splittedValue?.get(1)
                if(prefix.isNotEmpty()){
                    firstNumber = "-$firstNumber"
                }
                var result = firstNumber?.toDouble()?.plus(secondNumber?.toDouble()!!)
                inputText?.text = result.toString()
            }
            if(textValue?.contains("*") == true){
                val splittedValue = textValue?.split("*")
                var firstNumber = splittedValue?.get(0)
                var secondNumber = splittedValue?.get(1)
                if(prefix.isNotEmpty()){
                    firstNumber = "-$firstNumber"
                }
                var result = firstNumber?.toDouble()?.times(secondNumber?.toDouble()!!)
                inputText?.text = result.toString()
            }
            if(textValue?.contains("/") == true){
                val splittedValue = textValue?.split("/")
                var firstNumber = splittedValue?.get(0)
                var secondNumber = splittedValue?.get(1)
                if(prefix.isNotEmpty()){
                    firstNumber = "-$firstNumber"
                }
                if(secondNumber =="0"){
                    inputText?.text = "Cannot divide by zero"
                }
                else{
                    var result = firstNumber?.toDouble()?.div(secondNumber?.toDouble()!!)
                    inputText?.text = result.toString()
                }

            }

        }catch (e : ArithmeticException){
            e.printStackTrace()
        }
    }

    fun onOperationPressed(view : View){
        inputText?.text?.let{
            if(!onNegativeDigit(it.toString())){
                inputText?.append((view as Button).text)
            }
        }
    }
    //if the number is negative
    private fun onNegativeDigit(value : String): Boolean {
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("+") ||
                    value.contains("-")||
                    value.contains("/") ||
                    value.contains("*")
        }
    }
    fun onClearButtonClicked(view : View){
        inputText?.text = ""
    }
}