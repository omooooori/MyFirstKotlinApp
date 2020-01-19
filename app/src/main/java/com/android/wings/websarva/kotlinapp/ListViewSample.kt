package com.android.wings.websarva.kotlinapp

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_list_view_sample.*

class ListViewSample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_sample)

//        val arrayOfStrings = arrayOf<String>(
//            "zero",
//            "one",
//            "two",
//            "three",
//            "four",
//            "five",
//            "six",
//            "seven",
//            "eight",
//            "nine",
//            "ten")
//
//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfStrings)
//        list_view.adapter = adapter

        var arrayList: ArrayList<AudioDataModel> = ArrayList()

        arrayList.add(AudioDataModel("zero", "zeroaudio"))
        arrayList.add(AudioDataModel("one", "oneaudio"))
        arrayList.add(AudioDataModel("two", "twoaudio"))
        arrayList.add(AudioDataModel("three", "threeaudio"))
        arrayList.add(AudioDataModel("four", "fouraudio"))
        arrayList.add(AudioDataModel("five", "fiveaudio"))
        arrayList.add(AudioDataModel("six", "sixaudio"))
        arrayList.add(AudioDataModel("seven", "sevenaudio"))
        arrayList.add(AudioDataModel("eight", "eightaudio"))
        arrayList.add(AudioDataModel("nine", "nineaudio"))
        arrayList.add(AudioDataModel("ten", "tenaudio"))

        var adapter: NumberAdapter = NumberAdapter(this, arrayList)
        list_view.adapter = adapter
    }
}


class NumberAdapter : BaseAdapter {

    var context: Context?
    var arrayList: ArrayList<AudioDataModel> = ArrayList()


    constructor(context: Context, arrayList: ArrayList<AudioDataModel>) {
        this.context = context
        this.arrayList = arrayList
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item, parent, false)

            holder = ViewHolder()

            holder.text = view.findViewById(R.id.text_view_list_view)
            holder.image = view.findViewById(R.id.image_view_list_view)

            view.tag = holder

        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val textValue = holder.text
        textValue?.text = arrayList[position].textNumber

        val imageValue = holder.image

        var mediaPlayer: MediaPlayer?
        imageValue?.setOnClickListener {
            mediaPlayer = MediaPlayer.create(
                context, context?.resources!!.getIdentifier(arrayList[position].audioNumber, "raw", context?.packageName))
            mediaPlayer?.start()
        }
        return view
    }


    override fun getItem(position: Int): Any {
           return arrayList[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getCount(): Int {
        return arrayList.size
    }


    private class ViewHolder {
        var text: TextView? = null
        var image: ImageView? = null
    }

}