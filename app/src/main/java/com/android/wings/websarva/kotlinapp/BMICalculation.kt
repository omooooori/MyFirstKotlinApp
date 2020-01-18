package com.android.wings.websarva.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_bmicalculation.*

class BMICalculation : AppCompatActivity() {

    private var weight : Double = 0.0
    private var height : Double = 0.0

    private val boundary1 : Double = 18.50
    private val boundary2 : Double = 25.00
    private val boundary3 : Double = 30.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmicalculation)
    }

    fun calculate(view: View) {
        val edit_text_weight : EditText = findViewById(R.id.edit_text_weight)
        val edit_text_height : EditText = findViewById(R.id.edit_text_height)

        var bmiImage : ImageView = findViewById(R.id.image_view_bmi)

        weight = edit_text_weight?.text.toString().toDouble()
        height = edit_text_height?.text.toString().toDouble()

        var bmi: Float = calculateBMI(weight, height)

        text_view_bmi.text = bmi.toString()

        if (bmi < boundary1) {
            bmiImage.setImageResource(R.drawable.underweight)
        } else if (boundary1 <= bmi && bmi < boundary2) {
            bmiImage.setImageResource(R.drawable.healthy)
        } else if (boundary2 <= bmi && bmi < boundary3) {
            bmiImage.setImageResource(R.drawable.overweight)
        } else if (bmi >= boundary3) {
            bmiImage.setImageResource(R.drawable.obesity)
        }
    }

    private fun calculateBMI(weight : Double, height : Double) : Float {
        return (weight/((height*0.01)*(height*0.01))).toFloat()
    }
}
