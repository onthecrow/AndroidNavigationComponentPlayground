package com.onthecrow.navigationtest.test2

import androidx.fragment.app.Fragment
import com.onthecrow.navigationtest.navigation.NavigationContainerFragment

class Test2Fragment2 : NavigationContainerFragment() {

    override fun getFirstFragment(): Fragment = Test2Fragment21()
}