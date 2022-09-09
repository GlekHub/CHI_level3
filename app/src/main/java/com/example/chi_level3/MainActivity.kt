package com.example.chi_level3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.chi_level3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val url = "https://zoo-animal-api.herokuapp.com/animals/rand/10"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<Item>()
        val rq: RequestQueue = Volley.newRequestQueue(this)
        val jar = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            for (x in 0 until response.length())
                list.add(
                    Item(
                        response.getJSONObject(x).getString("name"),
                        response.getJSONObject(x).getString("animal_type"),
                        response.getJSONObject(x).getString("image_link")
                    )
                )

            val adp = ItemAdapter(this, list)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adp
        }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        })
        rq.add(jar)
    }

}