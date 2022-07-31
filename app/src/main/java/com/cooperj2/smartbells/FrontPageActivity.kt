package com.cooperj2.smartbells

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cooperj2.smartbells.databinding.ActivityFrontPageBinding


class FrontPageActivity : AppCompatActivity() {

    private var binding: ActivityFrontPageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrontPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Handler().postDelayed( {
            val mainIntent = Intent(this@FrontPageActivity, MainActivity::class.java)
            this@FrontPageActivity.startActivity(mainIntent)
            this@FrontPageActivity.finish()

            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 2000)

    }
}
