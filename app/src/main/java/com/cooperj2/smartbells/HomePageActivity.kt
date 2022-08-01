package com.cooperj2.smartbells

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cooperj2.smartbells.databinding.ActivityFrontPageBinding
import com.cooperj2.smartbells.databinding.ActivityHomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {
    private var binding: ActivityHomePageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.bottomNavigation?.selectedItemId = R.id.profile


        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@HomePageActivity, ProfilePage::class.java)
                    startActivity(intent)
                    true
                }
                R.id.history -> {
                    val intent = Intent(this@HomePageActivity, WorkoutHistory::class.java)
                    startActivity(intent)
                    true
                }
                R.id.itWorkout -> {
                    val intent = Intent(this@HomePageActivity, UserInputActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        binding?.bottomNavigation?.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.profile -> {
                    val intent = Intent(this@HomePageActivity, ProfilePage::class.java)
                    startActivity(intent)
                }
                R.id.history -> {
                    val intent = Intent(this@HomePageActivity, WorkoutHistory::class.java)
                    startActivity(intent)
                }
                R.id.itWorkout -> {
                    val intent = Intent(this@HomePageActivity, UserInputActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }


}