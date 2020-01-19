package com.android.wings.websarva.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_definition.*

class Definition : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition)

        val definition = intent.getStringExtra("myDefinition")

        text_view_definition_from_oxford.text = definition
    }


    fun onBack(view: View) {
        finish()
    }
}
