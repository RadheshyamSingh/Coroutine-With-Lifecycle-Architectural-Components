package com.radheshyam.coroutinelifecycledemo.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.radheshyam.coroutinelifecycledemo.R
import com.radheshyam.coroutinelivedatasample.viewmodel.FirstViewModel

class FirstFragment : Fragment() {

    private lateinit var button: Button

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.first_fragment, container, false)
        button = view.findViewById(R.id.first_button)
        button.setOnClickListener {
            (activity as LifecycleScopeActivity).showNextFragment()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
