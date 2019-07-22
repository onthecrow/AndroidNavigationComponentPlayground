package com.onthecrow.navigationtest.test3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.onthecrow.navigationtest.R
import com.onthecrow.navigationtest.setupWithNavController
import kotlinx.android.synthetic.main.activity_test_3.*

class Test3Activity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_3)
        if (savedInstanceState == null) {
            createBottomNavigation()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        createBottomNavigation()
    }

    private fun createBottomNavigation() {
        val controller = test_2_bottom_navigation.setupWithNavController(
                listOf(R.navigation.navigation5, R.navigation.navigation3, R.navigation.navigation4, R.navigation.navigation6),
                supportFragmentManager,
                R.id.nestedNavFragment,
                intent
        )
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onBackPressed() {
        if (currentNavController?.value?.navigateUp() != true) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp() = currentNavController?.value?.navigateUp() ?: false
}