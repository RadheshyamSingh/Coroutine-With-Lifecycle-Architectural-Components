package com.radheshyam.coroutinelifecycledemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.radheshyam.coroutinelifecycledemo.R
import com.radheshyam.coroutinelifecycledemo.viewmodel.MainActivityViewModel


/**
 * Activity used to show a demo for viewModelScope.
 *
 * author Radheshyam
 * date 26/May/2019
 */
class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        progressBar = findViewById(R.id.progressBar)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.employee.observe(this, Observer { employee ->
            println("Radhe In observer before")
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
            textView.text = employee.toString()
            println("Radhe In observer after")
        })
    }

    fun onHandleClick(view: View) {
        println("clicked view ${view.id}")
        progressBar.visibility = View.VISIBLE
        textView.visibility = View.GONE
        viewModel.loadEmployee()
    }
}
