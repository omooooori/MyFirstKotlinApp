package com.android.wings.websarva.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_guess_my_number.*

class GuessMyNumber : AppCompatActivity() {

    var number : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess_my_number)

//        var textView : TextView = findViewById(R.id.text_view_question)

        text_view_question.text = resources.getString(R.string.first_question)
        number = 5  // always start from 5
    }


    fun arrowDown(view: View) {
        if (number == 5) {
            number = 3
            text_view_question.text = resources.getString(R.string.second_question)
        } else if (number == 3) {
            number = 2
            text_view_question.text = resources.getString(R.string.third_question)
        } else if (number == 2) {
            number = 1
            text_view_question.text = resources.getString(R.string.fourth_question)
        } else if (number == 7) {
            number = 6
            text_view_question.text = resources.getString(R.string.seventh_question)
        } else if (number == 9) {
            number = 8
            text_view_question.text = resources.getString(R.string.ninth_question)
        }
    }


    fun arrowUp(view: View) {
        if (number == 3) {
            number = 4
            text_view_question.text = resources.getString(R.string.fifth_question)
        } else if (number == 5) {
            number = 7
            text_view_question.text = resources.getString(R.string.sixth_question)
        } else if (number == 7) {
            number = 9
            text_view_question.text = resources.getString(R.string.eighth_question)
        } else if (number == 9) {
            number = 10
            text_view_question.text = resources.getString(R.string.tenth_question)
        }
    }


    fun successFunction(view: View) {
        text_view_success.visibility = View.VISIBLE
        text_view_question.visibility = View.INVISIBLE
        text_view_targeted_number.visibility = View.VISIBLE

        text_view_targeted_number.text = String.format(resources.getString(R.string.target_number_text_view), number)
    }


    fun resetGame(view: View) {
        text_view_question.text = resources.getString(R.string.first_question)

        text_view_success.visibility = View.INVISIBLE
        text_view_targeted_number.visibility = View.INVISIBLE

        number = 5

        text_view_question.visibility = View.VISIBLE

    }
}
