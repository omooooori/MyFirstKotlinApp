package com.android.wings.websarva.kotlinapp

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_news_app.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset

class NewsApp : AppCompatActivity() {

    var newsList: ArrayList<NewsContentsModel> = ArrayList()
    var pageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_app)

//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 1",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 2",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 3",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 4",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 5",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 6",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 7",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 8",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 9",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//        newsList.add(NewsContentsModel(
//            "politics",
//            "The Guardian view on parliament's 10",
//            "https://www.theguardian.com/commentisfree/2019/dec/06/final-election-debate-boris-johnson-jeremy-corbyn")
//        )
//
        var adapter = NewsAdapter(this, newsList)
        list_view_news_contents.adapter = adapter

    }


    // Get the JSON data and show the 10 contents.
    fun searchTopic(view: View) {
        pageNumber = 1
        var stringUrl: String = "https://content.guardianapis.com/search?q=${edit_text_search_topic.text}&tag=politics/politics&page=${pageNumber}"

        newsList.clear()

        var myAsyncTask = MyAsyncTask()

        myAsyncTask.execute(stringUrl)
    }


    // Change the page number and show the another 10 contents.
    fun loadMore(view: View) {
        pageNumber++
        var stringUrl: String = "https://content.guardianapis.com/search?q=${edit_text_search_topic.text}&tag=politics/politics&page=${pageNumber}"

        var myAsyncTask = MyAsyncTask()

        myAsyncTask.execute(stringUrl)
    }


    fun updateUi(list: ArrayList<NewsContentsModel>) {
        list_view_news_contents?.adapter = NewsAdapter(this, list)
    }


    inner class MyAsyncTask: AsyncTask<String, Void, ArrayList<NewsContentsModel>>() {

        override fun onPostExecute(result: ArrayList<NewsContentsModel>?) {
            if (result != null) {
                updateUi(result)
            }
        }

        override fun doInBackground(vararg params: String?): ArrayList<NewsContentsModel>? {
            val url = createUrl(params[0])

            var jsonResponse: String? = null

            try {
                jsonResponse = makeHttpResponse(url)
            } catch (e: IOException) {
                Log.e("NewsApp Activity", "PROBLEM MAKING THE HTTP REQUEST : $e")
            }

            val data: ArrayList<NewsContentsModel>? = extractFeaturesFromJson(jsonResponse)
            return data

        }


        fun extractFeaturesFromJson(newsContentsJson: String?): ArrayList<NewsContentsModel>? {
            try {
                val baseJsonResponse = JSONObject(newsContentsJson)
                val response = baseJsonResponse.getJSONObject("response")
                val newsArray = response.getJSONArray("results")

                for (i in 0..9) {
                    val item = newsArray.getJSONObject(i)
                    val sectionName = item.getString("sectionName")
                    val webTitle = item.getString("webTitle")
                    val webUrl = item.getString("webUrl")

                    val data = NewsContentsModel(sectionName, webTitle, webUrl)

                    newsList.add(data)
                }

            } catch (e: JSONException) {
                Log.e("NewsApp Activity", "ERROR IN PARSING JSON $e")
            }
            return newsList
        }


        fun createUrl(stringUrl: String?): URL? {
            var url: URL? = null

            try {
                url = URL(stringUrl)
            } catch (e: MalformedURLException) {
                Log.e("NewsApp Activity", "ERROR IN CREATING URL")
                return null
            }

            return url
        }


        fun makeHttpResponse(url: URL?): String? {
            var jsonResponse: String? = null
            var urlConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null

            try {
                urlConnection = url?.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.setRequestProperty("Accept", "application/json")  // ?
                urlConnection.setRequestProperty("api-key", "Your api-key")
                urlConnection.readTimeout = 10000  // [ms]
                urlConnection.connectTimeout = 15000  // [ms]
                urlConnection.connect()

                // request is successful
                if (urlConnection.responseCode == 200) {
                    inputStream = urlConnection.inputStream
                    jsonResponse = readFromInputString(inputStream)
                } else {
                    Log.e("NewsApp Activity", "THE CODE IS ${urlConnection?.responseCode}")
                }

                urlConnection.disconnect()
                inputStream?.close()

            } catch (e: IOException) {
                Log.e("NewsApp Activity", "ERROR RESPONSE CODE ${urlConnection?.responseCode}")
            }

            return jsonResponse
        }


        fun readFromInputString(inputStream: InputStream): String {
            val output = StringBuilder()

            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
                val reader = BufferedReader(inputStreamReader)
                var line = reader.readLine()

                while (line != null) {
                    output.append(line)
                    line = reader.readLine()
                }
            }
            return output.toString()
        }

    }
}

class NewsAdapter(context: Context, arrayList: ArrayList<NewsContentsModel>) : BaseAdapter() {

    var arrayList: ArrayList<NewsContentsModel> = ArrayList()
    var context: Context? = context

    init {
        this.arrayList = arrayList
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        val inflater: LayoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val holder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.news_list_item, parent, false)

            holder = ViewHolder()

            holder.sectionName = view.findViewById(R.id.text_view_section_name)
            holder.webTitle = view.findViewById(R.id.text_view_web_title)

            view.tag = holder

        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val textValueSectionName: TextView? = holder.sectionName
        textValueSectionName?.text = arrayList[position].sectionName

        val textValueWebTitle: TextView? = holder.webTitle
        textValueWebTitle?.text = arrayList[position].webTitle

        textValueWebTitle?.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(arrayList[position].webUrl))
            context!!.startActivity(browserIntent)
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
        var sectionName: TextView? = null
        var webTitle: TextView? = null
    }
}
