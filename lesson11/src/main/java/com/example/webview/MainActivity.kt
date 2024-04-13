package com.example.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import com.example.webview.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.webView.settings.javaScriptEnabled = true
//        binding.webView.webViewClient = WebViewClient()
//        binding.webView.loadUrl("https://m.bing.com")
        binding.sendRequestBtn.setOnClickListener {
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithOkHttp() {
        //开启线程发起网络请求
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.boredapi.com/api/activity")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    parseJSONWithJSONObject(responseData)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun parseJSONWithJSONObject(jsonData: String){
        try {
/*            //使用jsonObject解析
            val jsonObject = JSONObject(jsonData)
            val activity =jsonObject.getString("activity")
            val type = jsonObject.getString("type")
            val participants = jsonObject.getString("participants")
            val price = jsonObject.getString("price")
            val accessibility = jsonObject.getString("accessibility")*/

            //使用gson解析
            val gson = Gson()
            val response = gson.fromJson(jsonData, App::class.java)
            Log.d("TAG", "type is ${response.type}")
            Log.d("TAG", "participants is ${response.participants}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    object HttpUtil{
        fun sendHttpRequest(address: String): String{
            var connection: HttpsURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpsURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use{
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                return response.toString()
            }catch (e: Exception){
                e.printStackTrace()
                return e.message.toString()
            }finally{
                connection?.disconnect()
            }
        }
    }

}








/*
val client = okHttpClient()
想要发起一条http请求，就需要创建一个request对象
val request = Request.Builder().build()
 */