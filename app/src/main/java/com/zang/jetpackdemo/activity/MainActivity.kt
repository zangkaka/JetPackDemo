package com.zang.jetpackdemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zang.jetpackdemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_next_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            main_next_btn.id -> {
                var intent = Intent(this, Main2Activity::class.java)
                startActivity(intent)
            }
        }
    }
}
