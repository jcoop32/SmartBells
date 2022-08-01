package com.cooperj2.smartbells

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cooperj2.smartbells.databinding.ActivityProfilePageBinding
import com.cooperj2.smartbells.databinding.ActivityWorkoutHistoryBinding

class WorkoutHistory : AppCompatActivity() {
    private var binding: ActivityWorkoutHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}