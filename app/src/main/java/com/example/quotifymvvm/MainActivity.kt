package com.example.quotifymvvm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.quotifymvvm.models.MainViewModel
import com.example.quotifymvvm.models.MainViewModelFactory
import com.example.quotifymvvm.models.QuoteDataClass

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var textQuote: TextView
    lateinit var textAuthor: TextView
    lateinit var textPrevious: TextView
    lateinit var textNext: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textQuote = findViewById(R.id.quoteText)
        textAuthor = findViewById(R.id.quoteAuthor)
        textPrevious = findViewById(R.id.previousText)
        textNext = findViewById(R.id.nextText)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(applicationContext)
        ).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())
    }

    fun setQuote(quoteDataClass: QuoteDataClass) {
        textQuote.text = quoteDataClass.text
        textAuthor.text = quoteDataClass.author

    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}