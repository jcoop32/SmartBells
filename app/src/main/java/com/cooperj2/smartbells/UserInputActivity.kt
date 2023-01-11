package com.cooperj2.smartbells

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cooperj2.smartbells.databinding.ActivityUserInputBinding
import com.google.firebase.database.FirebaseDatabase


class UserInputActivity : AppCompatActivity() {

    private var binding: ActivityUserInputBinding? = null
    private var repInc = 0
    private var weightInc = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInputBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarExercise2)


        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise2?.setNavigationOnClickListener {
            customDialogForBackButton()
        }

        val database = FirebaseDatabase.getInstance().getReference("users")
        
        //val name = intent.getStringExtra("name")


        val workoutNames = resources.getStringArray(com.cooperj2.smartbells.R.array.Exercises)

        if (binding?.spinner != null) {
            val adapter = ArrayAdapter(
                this,
                R.layout.simple_spinner_item, workoutNames
            )
            binding?.spinner!!.adapter = adapter
        }

        binding?.btnStart?.setOnClickListener {
            if (binding?.etReps?.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter Reps", Toast.LENGTH_SHORT).show()
            } else if (binding?.spinner?.selectedItem.toString() == ""){
                Toast.makeText(this, "Please Select Workout", Toast.LENGTH_SHORT).show()
            } else if (binding?.etWeight?.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter Weight", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@UserInputActivity, WorkoutActivity::class.java)
                startActivity(intent)
                finish()

                val rep: String = binding?.etReps?.text.toString()
                intent.putExtra("reps", rep)
                startActivity(intent)

                val weight: String = binding?.etWeight?.text.toString()
                intent.putExtra("weight", weight)
                startActivity(intent)

                val name = getIntent().getStringExtra("name")
                intent.putExtra("name", name)
                startActivity(intent)

                val wrkName: String = binding?.spinner?.selectedItem.toString()
                intent.putExtra("workout", wrkName)
                startActivity(intent)

            }
        }

        binding?.btnInc?.setOnClickListener{
                incRepVal()
        }

        binding?.btnDec?.setOnClickListener{
                decRepVal()
        }
    }


    private fun incRepVal(){
        repInc++
        binding?.etReps?.text = "$repInc"

    }

    private fun decRepVal(){
        repInc--
        binding?.etReps?.text = "$repInc"

    }


    fun plusFive(view: View) {
        weightInc+=5
        binding?.etWeight?.text = "$weightInc"
    }
    fun minusFive(view: View) {
        weightInc-=5
        binding?.etWeight?.text = "$weightInc"
    }
    fun minusTwoPoint(view: View) {
        weightInc-=2.5
        binding?.etWeight?.text = "$weightInc"

    }
    fun plusTwoPoint(view: View) {
        weightInc+=2.5
        binding?.etWeight?.text = "$weightInc"

    }

    fun minusTen(view: View) {
        weightInc -= 10
        binding?.etWeight?.text = "$weightInc"

    }

    fun plusTen(view: View) {
        weightInc += 10
        binding?.etWeight?.text = "$weightInc"
    }

    private fun customDialogForBackButton() {

        val goBack = Intent(this@UserInputActivity, LoginActivity::class.java)
        startActivity(goBack)
    }

}

