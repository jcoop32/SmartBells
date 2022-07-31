package com.cooperj2.smartbells

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cooperj2.smartbells.databinding.ActivityWorkoutActivityBinding


class WorkoutActivity : AppCompatActivity() {

    private var count = 0
    private var countTotal = 0
    private var set = 0
    private var binding: ActivityWorkoutActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutActivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            customDialogForBackButton()
        }

        binding?.btnRepIncrease?.setOnClickListener {
            repIn()
            repTotalCount()
        }

        binding?.btnWorkoutReset?.setOnClickListener {
            resetBtn()
        }

        val wrkoutName = intent.getStringExtra("workout")
        binding?.tvWorkoutName?.text = wrkoutName

        binding?.btnWorkoutFinish?.setOnClickListener {
                val intent = Intent(this@WorkoutActivity, FinishActivity::class.java)
                startActivity(intent)
                finish()
                intent.putExtra("repTotal", countTotal.toString())
                startActivity(intent)

                val str = getIntent().getStringExtra("name")
                intent.putExtra("name", str)
                startActivity(intent)

                val weight = getIntent().getStringExtra("weight")
                intent.putExtra("weight", weight)
                startActivity(intent)
        }
    }

    private fun repIn(){
        count++
        binding?.tvRepValue?.text = "$count"
        val reps =  intent.getStringExtra("reps")

        if (reps != null) {
            if ((reps.toInt() / count) == 0) {
                set++
                count = 0
                countTotal -= 1
                binding?.tvRepValue?.text = "$count"
                binding?.tvSetValue?.text = "$set"

            }
        }
    }

    private fun repTotalCount() {
        countTotal++
        binding?.tvRepTotalValue?.text = "$countTotal"
    }

    private fun resetBtn() {
        count = 0
        set = 0
        binding?.tvRepValue?.text = "$count"
        binding?.tvSetValue?.text = "$set"
    }

    private fun customDialogForBackButton(){
        val goBack = Intent(this@WorkoutActivity, UserInputActivity::class.java)
        startActivity(goBack)
    }
}