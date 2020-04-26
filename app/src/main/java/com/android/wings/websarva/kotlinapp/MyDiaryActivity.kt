package com.android.wings.websarva.kotlinapp

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_DATE
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_DIARY
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_TITLE
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.ID
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.TABLE_NAME
import com.android.wings.websarva.kotlinapp.data.MyDiary
import com.android.wings.websarva.kotlinapp.data.MyDiaryDbHelper
import kotlinx.android.synthetic.main.activity_my_diary.*

class MyDiaryActivity : AppCompatActivity() {

    private lateinit var dbHelper: MyDiaryDbHelper

    private var diaryList: ArrayList<MyDiary> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: MyDiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_diary)

        dbHelper = MyDiaryDbHelper(this)

        fb_create_new_diary.setOnClickListener {
            val intent = Intent(this, NewDiaryActivity::class.java)
            startActivity(intent)
        }

        displayDataInfo()
    }

    override fun onStart() {
        super.onStart()
        diaryList.clear()
        displayDataInfo()
    }

    private fun displayDataInfo() {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(ID, COLUMN_DATE, COLUMN_TITLE, COLUMN_DIARY)

        val cursor: Cursor = db.query(TABLE_NAME, projection, null, null, null, null, null)

        val idColumnIndex = cursor.getColumnIndexOrThrow(ID)
        val dateColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE)
        val titleColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE)
        val diaryColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DIARY)

        while (cursor.moveToNext()) {
            val currentId = cursor.getInt(idColumnIndex)
            val currentDate = cursor.getString(dateColumnIndex)
            val currentTitle = cursor.getString(titleColumnIndex)
            val currentDiary = cursor.getString(diaryColumnIndex)

            diaryList.add(MyDiary(currentId, currentDate, currentTitle, currentDiary))
        }
        cursor.close()

        linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager

        adapter = MyDiaryAdapter(diaryList)
        recycler_view.adapter = adapter
    }

}
