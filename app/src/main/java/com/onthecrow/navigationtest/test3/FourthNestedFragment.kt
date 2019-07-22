package com.onthecrow.navigationtest.test3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.onthecrow.navigationtest.R
import kotlinx.android.synthetic.main.fragment_fourth_nested.*

class FourthNestedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_fourth_nested, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fourthNestedFragmentTV.setOnClickListener {
            findNavController().navigate(R.id.fourthNestedFragment2)
        }
    }
}