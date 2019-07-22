package com.onthecrow.navigationtest.test2

import android.os.Bundle
import com.onthecrow.navigationtest.R
import com.onthecrow.navigationtest.navigation.NavigationActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Test2Activity : NavigationActivity<Test2NavigationRouter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_2)

        findViewById<BottomNavigationView>(R.id.test_2_bottom_navigation)
                .setOnNavigationItemSelectedListener(navigationRouter)
    }

    override fun showInitialFragment() = navigationRouter.showToday()

    override fun createNavigationRouter() = Test2NavigationRouter(this)

    override fun getContentLayoutId(): Int = R.id.test_2_container
}