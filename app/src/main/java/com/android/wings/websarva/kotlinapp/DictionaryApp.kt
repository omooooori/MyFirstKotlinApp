package com.android.wings.websarva.kotlinapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_dictionary_app.*
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

class DictionaryApp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_app)
    }


    fun findWord(view: View) {

        var stringUrl: String = "https://od-api.oxforddictionaries.com/api/v2/entries/en-gb/" + edit_text_search_word.text.toString()

        var myAsyncTask = MyAsyncTask()

        myAsyncTask.execute(stringUrl)

    }


    // For get definition data from Oxford via API.
    inner class MyAsyncTask: AsyncTask<String, Void, DictionaryData>() {
        override fun doInBackground(vararg params: String?): DictionaryData? {
            val url = createUrl(params[0])

            var jsonResponse: String?

            try {
                jsonResponse = makeHttpResponse(url)
                val data: DictionaryData? = extractFeatureFromJson(jsonResponse)
                return data
            } catch (e: IOException) {
                Log.e("DictionaryApp Activity", "PROBLEM MAKING THE HTTP REQUEST : " + e)
            }
            return null
        }

        override fun onPostExecute(result: DictionaryData?) {
            super.onPostExecute(result)

            if (result == null) {
                return
            }
            showDefinition(result.definition)
        }
    }


    fun showDefinition(definition: String?) {
        val intent = Intent(this, Definition::class.java)
        intent.putExtra("myDefinition", definition)

        startActivity(intent)
    }


    fun extractFeatureFromJson(definitionJson: String?): DictionaryData? {
        try {
            val baseJsonResponse = JSONObject(definitionJson)
            val featureResults = baseJsonResponse.getJSONArray("results")
            val firstResult = featureResults.getJSONObject(0)
            val lexicalEntries = firstResult.getJSONArray("lexicalEntries")
            val firstLexicalEntry = lexicalEntries.getJSONObject(0)
            val entries = firstLexicalEntry.getJSONArray("entries")
            val firstEntry = entries.getJSONObject(0)
            val senses = firstEntry.getJSONArray("senses")
            val firstSense = senses.getJSONObject(0)
            val definitions = firstSense.getJSONArray("definitions")

            Log.d("definition", "IT IS: " + definitions[0])

            return DictionaryData(definitions[0].toString())

        } catch (e: JSONException) {
            Log.e("DictionaryApp Activity", "ERROR IN PARSING JSON" + e)
        }
        return null
    }


    fun createUrl(stringUrl: String?): URL? {
        var url: URL? = null

        try {
            url = URL(stringUrl)
        } catch (exception: MalformedURLException) {
            Log.d("DictionaryApp Activity", "ERROR IN CREATING URL")
            return null
        }

        return url
    }


    fun makeHttpResponse(url: URL?): String? {
        var jsonResponse: String? = null
        var urlConnection: HttpURLConnection
        var inputStream: InputStream? = null

        try {
            urlConnection = url?.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.setRequestProperty("Accept", "Application/json")
            urlConnection.setRequestProperty("app_id", "Your app_id")
            urlConnection.setRequestProperty("app_key", "Your app_key")
            urlConnection.readTimeout = 10000  // [ms]
            urlConnection.connectTimeout = 15000  // [ms]
            urlConnection.connect()

            // request is successful
            if (urlConnection.responseCode == 200) {
                inputStream = urlConnection.inputStream
                jsonResponse = readFromInputString(inputStream)
            } else {
                Log.d("DictionaryApp Activity", "ERROR RESPONSE CODE" + urlConnection.responseCode)
            }

            urlConnection.disconnect()
            inputStream?.close()

        } catch (e: IOException) {
            Log.e("DictionaryApp Activity", "ERROR CONNECTION" + e)
        }

        return jsonResponse
    }


    fun readFromInputString(inputStream: InputStream): String {
        var output = StringBuilder()

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
