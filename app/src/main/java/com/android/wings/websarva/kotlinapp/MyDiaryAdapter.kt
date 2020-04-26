package com.android.wings.websarva.kotlinapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.ID
import com.android.wings.websarva.kotlinapp.data.DatabaseMoanager.MyDiaryEntry.TABLE_NAME
import com.android.wings.websarva.kotlinapp.data.MyDiary
import com.android.wings.websarva.kotlinapp.data.MyDiaryDbHelper
import kotlinx.android.synthetic.main.recycler_my_diary_item.view.*

class MyDiaryAdapter(private var diaryList: MutableList<MyDiary>)
    : RecyclerView.Adapter<MyDiaryAdapter.MyDiaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyDiaryViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.recycler_my_diary_item, parent, false)

        view.ib_delete_diary.setOnClickListener {
            val dbHelper = MyDiaryDbHelper(view.context)
            val db = dbHelper.writableDatabase

            val selection = "$ID = ?"
            val selectionArgs = arrayOf("${diaryList[position].id}")

            db.delete(TABLE_NAME, selection, selectionArgs)

            diaryList.removeAt(position)

            notifyDataSetChanged()
        }

        return MyDiaryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    override fun onBindViewHolder(holder: MyDiaryViewHolder, position: Int) {

        val item = diaryList[position]
        holder.bindDiary(item)

    }

    class MyDiaryViewHolder(v: View)
        : RecyclerView.ViewHolder(v)
        , View.OnClickListener {

        private lateinit var view: View
        private lateinit var diary: MyDiary
        private var date: TextView
        private var title: TextView

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, NewDiaryActivity::class.java)

            intent.putExtra("IdOfRow", diary.id)

            context.startActivity(intent)
        }

        init {
            view = v
            date = view.findViewById(R.id.tv_date)
            title = view.findViewById(R.id.tv_title)

            v.setOnClickListener(this)
        }

        fun bindDiary(diary: MyDiary) {
            this.diary = diary

            date.text = diary.date
            title.text = diary.title
        }
    }
}