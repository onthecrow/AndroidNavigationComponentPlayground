package com.onthecrow.navigationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        findViewById<Button>(R.id.open_test_1_button).setOnClickListener {
//            val intent = Intent(this, Test1Activity::class.java)
//            startActivity(intent)
//        }
//        findViewById<Button>(R.id.open_test_2_button).setOnClickListener {
//            val intent = Intent(this, Test2Activity::class.java)
//            startActivity(intent)
//        }
    }
}
