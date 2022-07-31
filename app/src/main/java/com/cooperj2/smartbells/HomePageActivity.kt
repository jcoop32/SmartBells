package com.cooperj2.smartbells

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cooperj2.smartbells.databinding.ActivityFrontPageBinding
import com.cooperj2.smartbells.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {
    private var binding: ActivityHomePageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}