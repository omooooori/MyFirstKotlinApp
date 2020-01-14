package com.android.wings.websarva.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.*

class DiceApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_app)

        val rollButton: Button = findViewById(R.id.button_id)

        val diceOne: ImageView = findViewById(R.id.first_dice)
        val diceTwo: ImageView = findViewById(R.id.second_dice)

        rollButton.setOnClickListener() {
            val randomNumber1 = Random().nextInt(6) + 1  // {from 0 to 5} + 1

            val resourceDrawable1 = when (randomNumber1) {
                1 -> R.drawable.dice1
                2 -> R.drawable.dice2
                3 -> R.drawable.dice3
                4 -> R.drawable.dice4
                5 -> R.drawable.dice5
                6 -> R.drawable.dice6
                else -> R.drawable.dice6
            }

            diceOne.setImageResource(resourceDrawable1)

            val randomNumber2 = Random().nextInt(6) + 1  // {from 0 to 5} + 1

            val resourceDrawable2 = when (randomNumber2) {
                1 -> R.drawable.dice1
                2 -> R.drawable.dice2
                3 -> R.drawable.dice3
                4 -> R.drawable.dice4
                5 -> R.drawable.dice5
                6 -> R.drawable.dice6
                else -> R.drawable.dice6
            }

            diceTwo.setImageResource(resourceDrawable2)
        }
    }
}
