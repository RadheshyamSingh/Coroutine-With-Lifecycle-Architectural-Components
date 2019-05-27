package com.radheshyam.coroutinelifecycledemo.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.radheshyam.coroutinelifecycledemo.R
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

            // this will run the showSnackBar on Main dispatcher and will lead to crash
            // if we press back after clicking on load button and before 2 sec.
            CoroutineScope(Dispatchers.Main).launch {
                delay(2000)
                showSnackBar(view)
            }

            // Here since showSnackBar is tied with lifeCycleScope of fragment's view
            // so this task will get cancelled once back is pressed in between
            // load button click and 2 second after click
            viewLifecycleOwner.lifecycleScope.launch {
                delay(2000)
                showSnackBar(view)
            }
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun showSnackBar(view: View) {
        val snackbar = Snackbar.make(view, "This is main activity", Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}
