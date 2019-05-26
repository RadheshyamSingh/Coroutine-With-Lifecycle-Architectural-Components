package com.radheshyam.coroutinelivedatademo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.radheshyam.coroutinelivedatademo.R
import com.radheshyam.coroutinelivedatademo.viewmodel.MainActivityViewModel

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
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
            textView.text = employee.toString()
        })

        viewModel.employee2.observe(this, Observer { employee ->
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
            textView.text = employee.toString()
        })
    }


    fun onHandleClick(view: View) {
        progressBar.visibility = View.VISIBLE
        textView.visibility = View.GONE
        viewModel.loadEmployee()
    }
}
