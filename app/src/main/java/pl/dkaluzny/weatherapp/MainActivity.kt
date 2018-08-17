package pl.dkaluzny.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }

        })


    }


}

class HomeFeed(val videos: List<Video>)

class Video(val id: Int, val name: String, val link: String, val imageUrl: String, val numbeOfviews: Int)

class Channel(val name: String, val profileImageUrl: String, val numberOfSubscribers: Int)