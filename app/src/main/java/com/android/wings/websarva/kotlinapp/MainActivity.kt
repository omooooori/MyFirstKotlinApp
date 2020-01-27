package com.android.wings.websarva.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lvScreen: ListView = findViewById(R.id.list_view_main)
        lvScreen.onItemClickListener = ListItemClickListener()
    }

    inner class ListItemClickListener: AdapterView.OnItemClickListener {

        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            val item = parent?.getItemAtPosition(position) as String

            val show = "You choose the screen : $item"
            Toast.makeText(this@MainActivity, show, Toast.LENGTH_SHORT).show()

            if (item == "Happy New Year App") {
                val intent = Intent(this@MainActivity, HappyNewYearApp::class.java)
                startActivity(intent)
            }

            if (item == "Mail App") {
                val intent = Intent(this@MainActivity, MailApp::class.java)
                startActivity(intent)
            }

            if (item == "Dice App") {
                val intent = Intent(this@MainActivity, DiceApp::class.java)
                startActivity(intent)
            }

            if (item == "Calculator App") {
                val intent = Intent(this@MainActivity, CalculatorApp::class.java)
                startActivity(intent)
            }

            if (item == "Guess My Number App") {
                val intent = Intent(this@MainActivity, GuessMyNumber::class.java)
                startActivity(intent)
            }

            if (item == "BMI Calculation App") {
                val intent = Intent(this@MainActivity, BMICalculation::class.java)
                startActivity(intent)
            }

            if (item == "ListView Sample App") {
                val intent = Intent(this@MainActivity, ListViewSample::class.java)
                startActivity(intent)
            }

            if (item == "Dictionary App") {
                val intent = Intent(this@MainActivity, DictionaryApp::class.java)
                startActivity(intent)
            }

            if (item == "News App") {
                val intent = Intent(this@MainActivity, NewsApp::class.java)
                startActivity(intent)
            }

            if (item == "ConstraintLayout Practice") {
                val intent = Intent(this@MainActivity, ConstraintLayoutApp::class.java)
                startActivity(intent)
            }

            if (item == "Fitness App") {
                val intent = Intent(this@MainActivity, FitnessApp::class.java)
                startActivity(intent)
            }
        }
    }
}
