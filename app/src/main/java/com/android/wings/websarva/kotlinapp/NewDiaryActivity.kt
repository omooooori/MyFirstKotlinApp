package com.android.wings.websarva.kotlinapp

import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_DATE
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_DIARY
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_TITLE
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.ID
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.TABLE_NAME
import com.android.wings.websarva.kotlinapp.data.MyDiary
import com.android.wings.websarva.kotlinapp.data.MyDiaryDbHelper
import kotlinx.android.synthetic.main.activity_new_diary.*
import java.text.SimpleDateFormat
import java.util.*

class NewDiaryActivity : AppCompatActivity() {

    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_diary)

        id = intent.getIntExtra("IdOfRow", 0)

        Log.d("NewDiaryActivity", "The passed id is: $id ")

        if (id != 0) {
            readDiary(id)
        }

        val currentDate = SimpleDateFormat("EEE, d MMM yyyy")

        tv_current_date.text = currentDate.format(Date())

    }

    private fun readDiary(id: Int) {
        val dbHelper = MyDiaryDbHelper(this)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(COLUMN_DATE, COLUMN_TITLE, COLUMN_DIARY)

        val selection = "$ID = ?"
        val selectionArgs = arrayOf("$id")

        val cursor: Cursor = db.query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null, null, null
        )

        val dateColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE)
        val titleColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE)
        val diaryColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DIARY)

        while (cursor.moveToNext()) {
            val currentDate = cursor.getString(dateColumnIndex)
            val currentTitle = cursor.getString(titleColumnIndex)
            val currentDiary = cursor.getString(diaryColumnIndex)

            tv_current_date.text = currentDate
            et_diary_title.setText(currentTitle)
            et_diary_content.setText(currentDiary)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.action_bar_menu, menu)

        return true
    }

    private fun insertDiary() {
        val dateString = tv_current_date.text.toString()
        val titleString = et_diary_title.text.toString().trim() {it <= ' '}
        val diaryString = et_diary_content.text.toString().trim() {it <= ' '}

        val dbHelper = MyDiaryDbHelper(this)

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_DATE, dateString)
            put(COLUMN_TITLE, titleString)
            put(COLUMN_DIARY, diaryString)
        }

        val rowId = db.insert(TABLE_NAME, null, values)

        if (rowId.equals(-1)) {
            Toast.makeText(this, "problem in inserting new diary.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "diary has been inserted $rowId", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateDiary(id: Int) {
        val dbHelpr = MyDiaryDbHelper(this)
        val db = dbHelpr.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_TITLE, et_diary_title.text.toString())
            put(COLUMN_DIARY, et_diary_content.text.toString())
        }

        db.update(TABLE_NAME, values, "$ID = $id", null)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_diary -> {

                if (id == 0) {
                    insertDiary()
                } else {
                    updateDiary(id)
                }

                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (id == 0) {
            insertDiary()
        } else {
            updateDiary(id)
        }
    }
}
