package com.onthecrow.navigationtest.test1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onthecrow.navigationtest.R

class Test1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_1)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.test_1_container, Test1Fragment1())
                    .commit()
        }
    }
}