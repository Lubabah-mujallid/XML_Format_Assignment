package com.example.xml_format_assignment

import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class MyXmlPullParserHandler {
    private val students = ArrayList<Students>()
    private var text: String? = null

    private var name = ""
    private var id = 0
    private var score = 0

    fun parse(inputStream: InputStream): ArrayList<Students> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("name", ignoreCase = true) -> { name = text.toString() }
                        tagName.equals("id", ignoreCase = true) -> { id = text!!.toInt() }
                        tagName.equals("marks", ignoreCase = true) -> { score = text!!.toInt() }
                        tagName.equals("StudentDetails", ignoreCase = true) -> { }
                        else -> students.add(Students(id, name, score))
                    }
                    else -> { }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return students
    }
}