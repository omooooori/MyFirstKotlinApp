package com.android.wings.websarva.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class CalculatorApp : AppCompatActivity() {

    var number : String = ""
    var numberOne : Float = 0.0F
    var numberTwo : Float = 0.0F
    var isAddition : Boolean = false
    var isSubdraw : Boolean = false
    var isTimes : Boolean = false
    var isDivide : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_app)
    }

    fun operationFunction(view: View) {

        var editText : EditText = findViewById(R.id.edit_text)

        when(view.id) {
            R.id.button_one -> {
                number += "1"
                editText.setText(number)
            }
            R.id.button_two -> {
                number += "2"
                editText.setText(number)
            }
            R.id.button_three -> {
                number += "3"
                editText.setText(number)
            }
            R.id.button_four -> {
                number += "4"
                editText.setText(number)
            }
            R.id.button_five -> {
                number += "5"
                editText.setText(number)
            }
            R.id.button_six -> {
                number += "6"
                editText.setText(number)
            }
            R.id.button_seven -> {
                number += "7"
                editText.setText(number)
            }
            R.id.button_eight -> {
                number += "8"
                editText.setText(number)
            }
            R.id.button_nine -> {
                number += "9"
                editText.setText(number)
            }
            R.id.button_zero -> {
                number += "0"
                editText.setText(number)
            }
            R.id.button_clear -> {
                number = ""
                editText.setText("")
            }
            R.id.button_plus -> {
                if (number.isNullOrBlank()) {
                    editText.setText("")
                } else {
                    numberOne = number.toFloat()
                    editText?.setText("")
                    isAddition = true
                    number = ""
                }
            }
            R.id.button_minus -> {
                if (number.isNullOrBlank()) {
                    editText.setText("")
                } else {
                    numberOne = number.toFloat()
                    editText?.setText("")
                    isSubdraw = true
                    number = ""
                }
            }
            R.id.button_time -> {
                if (number.isNullOrBlank()) {
                    editText.setText("")
                } else {
                    numberOne = number.toFloat()
                    editText?.setText("")
                    isTimes = true
                    number = ""
                }
            }
            R.id.button_divide -> {
                if (number.isNullOrBlank()) {
                    editText.setText("")
                } else {
                    numberOne = number.toFloat()
                    editText?.setText("")
                    isDivide = true
                    number = ""
                }
            }
            R.id.button_equal -> {

                if (isAddition) {
                    numberTwo = number.toFloat()
                    editText?.setText((numberOne + numberTwo).toString())
                }
                if (isSubdraw) {
                    numberTwo = number.toFloat()
                    editText?.setText((numberOne - numberTwo).toString())
                }
                if (isTimes) {
                    numberTwo = number.toFloat()
                    editText?.setText((numberOne * numberTwo).toString())
                }
                if (isDivide) {
                    numberTwo = number.toFloat()
                    editText?.setText((numberOne / numberTwo).toString())
                }

            }
        }
    }
}
