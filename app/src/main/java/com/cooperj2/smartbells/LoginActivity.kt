package com.cooperj2.smartbells

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cooperj2.smartbells.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val preferences = getSharedPreferences("checkbox", MODE_PRIVATE)
        val checkbox = preferences.getString("remember", "")
        //if (checkbox.equals("true")){
        // val intent = Intent(this@LoginActivity, UserInputActivity::class.java)
        // startActivity(intent)
        // }else if (checkbox.equals("false")){
        // Toast.makeText(this, "please login", Toast.LENGTH_SHORT).show()

        //  }

        binding?.btnNext?.setOnClickListener {
            if (binding?.etEmail?.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter a Email", Toast.LENGTH_SHORT).show()
            } else if (binding?.etPassword?.text.toString().isEmpty()) {
                Toast.makeText(this, "Please Enter a Password", Toast.LENGTH_SHORT).show()
            } else if (binding?.etPassword?.text.toString()
                    .isNotEmpty() && binding?.etPassword?.text.toString().isNotEmpty()
            ) {
                firebaseAuth.signInWithEmailAndPassword(
                    binding?.etEmail?.text.toString(),
                    binding?.etPassword?.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this@LoginActivity, UserInputActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Please check if all fields above are correct." +
                                    " If you don't have an account register now.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    fun register(view: View) {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(intent)

    }

    fun checkedBox(view: View) {
        if (binding?.cbRemember?.isChecked == true) {
            val preferences = getSharedPreferences("checkbox", MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("remember", "true")
            editor.apply()

        } else if (binding?.cbRemember?.isChecked == false) {
            val preferences = getSharedPreferences("checkbox", MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("remember", "false")
            editor.apply()
        }
    }


}




