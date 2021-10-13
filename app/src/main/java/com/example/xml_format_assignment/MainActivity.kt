package com.example.xml_format_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var myList: ArrayList<Students>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myList = ArrayList()

        //peraser
        try{
            val parser = MyXmlPullParserHandler()
            val iStream = assets.open("studentdetails.xml")
            myList = parser.parse(iStream)

        }catch (e: IOException) {
            println("ISSUE: $e")
        }

        //recycler
        val adapter = RecyclerAdapter(this, myList)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)



    }
}