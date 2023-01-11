package com.cooperj2.smartbells

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cooperj2.smartbells.databinding.ActivityFinishBinding
import com.cooperj2.smartbells.databinding.CustomDialogBackBinding
import kotlin.math.pow
import kotlin.math.roundToLong

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.btnFinishDone?.setOnClickListener {
            customDialogForBackButton()
        }

        binding?.btnFinishRedo?.setOnClickListener {
            val intent = Intent(this@FinishActivity, UserInputActivity::class.java)
            startActivity(intent)
            finish()

        }

        val total = intent.getStringExtra("repTotal")
        binding?.tvFinishRepValue?.text = "$total"

        val name = intent.getStringExtra("name")
        binding?.tvUsernameFinish?.text = "$name"

        val weight = intent.getStringExtra("weight")
        binding?.tvTotalRepsAt?.text = "total reps at $weight lbs:"

        binding?.tvTotalWeightValue?.text = "${getTotalWeightLifted()}lbs"
        binding?.tv1repMax?.text = "estimated 1 rep max: ${getOneRepMax()} lbs"

    }

    private fun getTotalWeightLifted(): Float {
        val totalRep = intent.getStringExtra("repTotal")
        val weight = intent.getStringExtra("weight")
        return (totalRep.toString().toInt()) * (weight.toString().toFloat())

    }

    private fun customDialogForBackButton() {
        val customDialog = Dialog(this)
        val dialogBinding = CustomDialogBackBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)

        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            val intent = Intent(this@FinishActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun getOneRepMax(): Float {
        val totalRep = intent.getStringExtra("repTotal")
        val weight = intent.getStringExtra("weight")

        val repMax = (100.0 * weight.toString().toFloat()) / (52.2 + 41.9 * Math.E.pow(
            (-0.055 * totalRep.toString().toInt())
        ))

        return repMax.roundToLong().toFloat()
    }
}


