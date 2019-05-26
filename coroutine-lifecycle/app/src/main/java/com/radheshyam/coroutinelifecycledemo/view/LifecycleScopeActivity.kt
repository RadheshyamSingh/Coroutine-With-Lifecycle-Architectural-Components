package com.radheshyam.coroutinelifecycledemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.radheshyam.coroutinelifecycledemo.R


class LifecycleScopeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lifecycle_scope_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FirstFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun showNextFragment() {
        val frag = SecondFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.container, frag).addToBackStack(null).commit()
    }

}
