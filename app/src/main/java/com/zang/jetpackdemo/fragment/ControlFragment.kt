package com.zang.jetpackdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.zang.jetpackdemo.MedalViewModel

import com.zang.jetpackdemo.R
import kotlinx.android.synthetic.main.fragment_control.*

/**
 * A simple [Fragment] subclass.
 */
class ControlFragment : Fragment(), View.OnClickListener {

    private lateinit var mMedalViewModel: MedalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_control, container, false)
        mMedalViewModel = ViewModelProviders.of(activity!!).get(MedalViewModel::class.java)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayMedal()
        control_minus_gold_btn.setOnClickListener(this)
        control_plus_gold_btn.setOnClickListener(this)
        control_minus_sliver_btn.setOnClickListener(this)
        control_plus_sliver_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            control_minus_gold_btn.id -> {
                mMedalViewModel.numberOfGoldMedal.value =
                    mMedalViewModel.numberOfGoldMedal.value!!.minus(1)
                displayMedal()
            }
            control_plus_gold_btn.id -> {
                mMedalViewModel.numberOfGoldMedal.value =
                    mMedalViewModel.numberOfGoldMedal.value!!.plus(1)
                displayMedal()
            }
            control_minus_sliver_btn.id -> {
                mMedalViewModel.numberOfSliverMedal.value =
                    mMedalViewModel.numberOfSliverMedal.value!!.minus(1)
                displayMedal()
            }
            control_plus_sliver_btn.id -> {
                mMedalViewModel.numberOfSliverMedal.value =
                    mMedalViewModel.numberOfSliverMedal.value!!.plus(1)
                displayMedal()
            }
        }
    }

    private fun displayMedal() {
        control_count_gold_txt.text = mMedalViewModel.numberOfGoldMedal.value.toString()
        control_count_sliver_txt.text = mMedalViewModel.numberOfSliverMedal.value.toString()
    }

}
