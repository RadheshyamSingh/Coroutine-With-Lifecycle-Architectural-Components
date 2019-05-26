package com.radheshyam.coroutinelifecycledemo.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.radheshyam.coroutinelifecycledemo.R
import com.radheshyam.coroutinelifecycledemo.model.Employee
import com.radheshyam.coroutinelivedatasample.viewmodel.SecondViewModel
import kotlinx.coroutines.*

class SecondFragment : Fragment(), LifecycleOwner {

    private lateinit var backButton: Button
    private lateinit var loadButton: Button
    private lateinit var dataText: TextView

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.second_fragment, container, false)
        backButton = view.findViewById(R.id.second_button)
        loadButton = view.findViewById(R.id.load_button)
        dataText = view.findViewById(R.id.second_textView)
        backButton.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        loadButton.setOnClickListener {
            //CoroutineScope(Dispatchers.Main).launch {
            viewLifecycleOwner.lifecycleScope.launch {
                var emps: List<Employee>? = null
                withContext(Dispatchers.IO) {
                    emps = viewModel.loadEmployee()
                }
                dataText.text = emps?.toString()
                val size = emps?.size ?: 0
                val mulSize = size * 10
                println("Radhe In main scope before $mulSize")
            }
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
