package com.example.weather.logic

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.weather.logic.model.Place
import com.example.weather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}






/*6)
一般在仓库层中定义的方法，为了能将异步获取的数据以响应式编程的方式通知给上一层，通常会返回一个LiveData对象
liveData()函数是lifecycle-livedata-ktx库提供的一个非常强大且好用的功能，它可以自动构建并返回一个LiveData对象，
然后在它的代码块中提供一个挂起函数的上下文,这样就可以在liveData()函数的代码块中调用任意的挂起函数.

这里调用了SunnyWeatherNetwork的searchPlaces()函数来搜索城市数据，然后
判断如果服务器响应的状态是ok，那么就使用Kotlin内置的Result.success()方法来包装获
取的城市数据列表，否则使用Result.failure()方法来包装一个异常信息。最后使用一个
emit()方法将包装的结果发射出去.

将liveData()函数的线程参数类型指定成了Dispatchers.IO，这样代码块中的所有代码就都运行在子线程中。Android是
不允许在主线程中进行网络请求，诸如读写数据库之类的本地数据操作也是不建议在主线程中进行
*/