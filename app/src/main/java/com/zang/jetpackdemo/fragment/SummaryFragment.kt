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
import kotlinx.android.synthetic.main.fragment_summary.*

/**
 * A simple [Fragment] subclass.
 */
class SummaryFragment : Fragment() {

    private lateinit var mMedalViewModel: MedalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_summary, container, false)
        mMedalViewModel = ViewModelProviders.of(activity!!).get(MedalViewModel::class.java)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMedalViewModel.numberOfGoldMedal.observe(this, Observer<Int> { displayMedal() })
        mMedalViewModel.numberOfSliverMedal.observe(this, Observer<Int> { displayMedal() })
    }

    private fun displayMedal() {
        var total: Int =
            mMedalViewModel.numberOfGoldMedal.value!!.plus(mMedalViewModel.numberOfSliverMedal.value!!)
        summary_total_txt.text = total.toString()
    }

}
