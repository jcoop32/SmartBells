package com.cooperj2.smartbells

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cooperj2.smartbells.databinding.ActivityHomePageBinding
import com.cooperj2.smartbells.databinding.ActivityProfilePageBinding

class ProfilePage : AppCompatActivity() {
    private var binding: ActivityProfilePageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePageBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}