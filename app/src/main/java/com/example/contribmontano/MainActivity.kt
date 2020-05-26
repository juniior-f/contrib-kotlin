package com.example.contribmontano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var model: MontanoViewModel
    private lateinit var adapter:ArrayAdapter<String>
    private var list = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter

        this.model = ViewModelProvider(this)[MontanoViewModel::class.java]
        this.model.getRepositories().observe(this, Observer {
                it.forEach {repository->
                    list.add("${repository.name}\n||->  ${repository.html_url}")
                    adapter.notifyDataSetChanged()
                }
        })
    }
}
