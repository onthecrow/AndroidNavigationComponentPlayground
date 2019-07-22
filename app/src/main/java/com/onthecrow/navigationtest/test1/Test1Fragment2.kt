package com.onthecrow.navigationtest.test1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.onthecrow.navigationtest.R
import kotlinx.android.synthetic.main.fragment_2_test_1.*

class Test1Fragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_2_test_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment2Test1.setOnClickListener {
            findNavController().navigate(R.id.test1Fragment3)
        }
    }
}