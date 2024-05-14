package com.example.quotifymvvm.models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    private var quoteList: Array<QuoteDataClass> = emptyArray()
    private var indexedValue = 0

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<QuoteDataClass> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<QuoteDataClass>::class.java)
    }

    fun getQuote() = quoteList[indexedValue]

    fun nextQuote() = quoteList[++indexedValue]

    fun previousQuote() = quoteList[--indexedValue]


}