package com.zang.jetpackdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zang.jetpackdemo.MedalViewModel

import com.zang.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var mMedalViewModel: MedalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        mMedalViewModel = ViewModelProviders.of(activity!!).get(MedalViewModel::class.java)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMedalViewModel.numberOfGoldMedal.observe(this, Observer<Int> { displayMedal() })
        mMedalViewModel.numberOfSliverMedal.observe(this, Observer<Int> { displayMedal() })
    }

    private fun displayMedal() {
        detail_gold_txt.text = mMedalViewModel.numberOfGoldMedal.value!!.toString()
        detail_sliver_txt.text = mMedalViewModel.numberOfSliverMedal.value!!.toString()
    }

}
