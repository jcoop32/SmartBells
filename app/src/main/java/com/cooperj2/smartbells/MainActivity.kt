package com.cooperj2.smartbells

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cooperj2.smartbells.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Handler().postDelayed({
            val mainIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()

            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 2000)

    }
}
