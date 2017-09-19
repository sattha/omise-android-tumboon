package com.omise.mobile.omisetumboon.ui.main

import android.os.Bundle
import com.omise.mobile.omisetumboon.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var fragment: MainFragment;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportFragmentManager.findFragmentById(R.id.contentContainer) ?:
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.contentContainer, fragment)
                        .commit()
    }
}
