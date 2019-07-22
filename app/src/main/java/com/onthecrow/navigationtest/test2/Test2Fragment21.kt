package com.onthecrow.navigationtest.test2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onthecrow.navigationtest.R
import com.onthecrow.navigationtest.navigation.NavigationFragment

class Test2Fragment21 : NavigationFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_2_1_test_2, container, false)
    }
}