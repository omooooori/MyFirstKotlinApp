package com.android.wings.websarva.kotlinapp.data

import android.provider.BaseColumns

object DatabaseMoanager {

    object MyDiaryEntry: BaseColumns {

        const val TABLE_NAME = "diaries"
        const val ID = BaseColumns._ID

        const val COLUMN_DATE = "date"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DIARY = "diary"

    }

}