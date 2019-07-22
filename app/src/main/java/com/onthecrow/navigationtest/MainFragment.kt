package com.onthecrow.navigationtest

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment

class MainFragment : NavHostFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavHostFragment.findNavController(this).navigate(R.id.testFragment)
    }
}