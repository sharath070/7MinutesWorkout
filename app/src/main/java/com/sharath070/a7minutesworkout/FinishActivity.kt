package com.sharath070.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sharath070.a7minutesworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private var _binding: ActivityFinishBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarFinishActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarFinishActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnFinish.setOnClickListener {
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}