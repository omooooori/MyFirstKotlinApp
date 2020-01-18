package com.android.wings.websarva.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class CalculatorApp : AppCompatActivity() {

    // This initialization is called every time when I push the button.
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

        var editText1 : EditText = findViewById(R.id.edit_text1)
        var editText2 : EditText = findViewById(R.id.edit_text2)

        when(view.id) {
            R.id.button_one -> {
                number += "1"
                editText2.setText(number)
            }
            R.id.button_two -> {
                number += "2"
                editText2.setText(number)
            }
            R.id.button_three -> {
                number += "3"
                editText2.setText(number)
            }
            R.id.button_four -> {
                number += "4"
                editText2.setText(number)
            }
            R.id.button_five -> {
                number += "5"
                editText2.setText(number)
            }
            R.id.button_six -> {
                number += "6"
                editText2.setText(number)
            }
            R.id.button_seven -> {
                number += "7"
                editText2.setText(number)
            }
            R.id.button_eight -> {
                number += "8"
                editText2.setText(number)
            }
            R.id.button_nine -> {
                number += "9"
                editText2.setText(number)
            }
            R.id.button_zero -> {
                number += "0"
                editText2.setText(number)
            }
            R.id.button_clear -> {
                number = ""
                editText1.setText("")
                editText2.setText("")
            }
            R.id.button_plus -> {
                if (number.isNullOrBlank()) {
                    editText2.setText("")
                } else {
                    numberOne = number.toFloat()
                    editText1?.setText(numberOne.toString())
                    editText2?.setText("")
                    isAddition = true
                    number = ""
                }
            }
            R.id.button_minus -> {
                if (number.isNullOrBlank()) {
                    editText2.setText("")
                } else {
                    numberOne = number.toFloat()
                    editText1?.setText(numberOne.toString())
                    editText2?.setText("")
                    isSubdraw = true
                    number = ""
                }
            }
            R.id.button_time -> {
                if (number.isNullOrBlank()) {
                    editText2.setText("")
                } else {
                    numberOne = number.toFloat()
                    editText1?.setText(numberOne.toString())
                    editText2?.setText("")
                    isTimes = true
                    number = ""
                }
            }
            R.id.button_divide -> {
                if (number.isNullOrBlank()) {
                    editText2.setText("")
                } else {
                    numberOne = number.toFloat()
                    editText1?.setText(numberOne.toString())
                    editText2?.setText("")
                    isDivide = true
                    number = ""
                }
            }
            R.id.button_equal -> {
                if (number.isNullOrBlank()) {
                    editText2.setText("")
                } else {
                    numberTwo = number.toFloat()

                    if (isAddition) {
                        editText1?.setText((numberOne + numberTwo).toString())
                    }
                    if (isSubdraw) {
                        editText1?.setText((numberOne - numberTwo).toString())
                    }
                    if (isTimes) {
                        editText1?.setText((numberOne * numberTwo).toString())
                    }
                    if (isDivide) {
                        editText1?.setText((numberOne / numberTwo).toString())
                    }
                }
                
                number = ""
                editText2?.setText("")
            }
        }
    }
}
