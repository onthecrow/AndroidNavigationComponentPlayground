package com.onthecrow.navigationtest.test2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onthecrow.navigationtest.R

class Test2Fragment11 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_1_1_test_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.fragment_text).setOnClickListener {
//            getContainerFragment().replaceFragmentWithBackStack(Test2Fragment12())
//            findNavController().navigate(R.id.test2Fragment12)
        }
    }
}