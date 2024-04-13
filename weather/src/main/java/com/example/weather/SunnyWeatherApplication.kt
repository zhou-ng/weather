package com.example.weather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object{
        const val TOKEN = "JvPFmT44zDaKsQJJ"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}

/*
1）需要在AndroidManifest.xml文件的<application>标签下指定SunnyWeatherApplication
经过这样配置之后就可以在项目的任何位置通过调用SunnyWeatherApplication.context来获取context对象
另外token也需要配置其中
*/
