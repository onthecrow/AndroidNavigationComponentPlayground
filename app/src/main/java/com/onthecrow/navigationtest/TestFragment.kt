package com.onthecrow.navigationtest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.onthecrow.navigationtest.test3.Test3Activity
import kotlinx.android.synthetic.main.fragment_main.*

class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentButton1.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.test1Fragment1) }
        mainFragmentButton2.setOnClickListener {
//            NavHostFragment.findNavController(this).navigate(R.id.test3Fragment)
            startActivity(Intent(context, Test3Activity::class.java))
            activity?.finish()
        }
    }
}