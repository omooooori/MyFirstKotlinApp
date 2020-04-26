package com.android.wings.websarva.kotlinapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_DATE
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_DIARY
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.COLUMN_TITLE
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.ID
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.TABLE_NAME

class MyDiaryDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mydiaries.db"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_DIARY_TABLE = "CREATE TABLE $TABLE_NAME (" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_DATE TEXT, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_DIARY TEXT )"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(SQL_CREATE_DIARY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(SQL_DELETE_ENTRIES)
    }
}