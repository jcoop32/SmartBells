package com.cooperj2.smartbells

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cooperj2.smartbells.databinding.ActivityRegisterBinding
import com.cooperj2.smartbells.databinding.CustomDialogBackMainRegBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    private var binding: ActivityRegisterBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        firebaseAuth= FirebaseAuth.getInstance()

        val database = FirebaseDatabase.getInstance().reference

        binding?.btnReg?.setOnClickListener{
            val name = binding?.etRegFName?.text.toString()
            val email: String = binding?.etRegEmail?.text.toString()
            val password = binding?.etRegPassword?.text.toString()
            val conPassword = binding?.etConfPassword?.text.toString()
            val phoneNumber = binding?.etRegPhoneNumber?.text.toString()


            if (email.isEmpty()){
                Toast.makeText(this, "Please Enter an Email", Toast.LENGTH_SHORT).show()
            } else if (name.isEmpty()) {
            Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show()
            } else if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()){
                Toast.makeText(this, "Please Enter a Password", Toast.LENGTH_SHORT).show()
            } else if (password.length < 8){
                Toast.makeText(this, "minimum 8 characters", Toast.LENGTH_SHORT).show()
            } else if (password != conPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }else if (conPassword.isEmpty()){
                Toast.makeText(this, "Please confirm password", Toast.LENGTH_SHORT).show()
            } else if (password == conPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(
                    email,
                    password
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        database.child("users").child(phoneNumber).setValue(Users(name, email))
                        binding?.tvRegSuccess?.visibility = View.VISIBLE
                        binding?.tvRegUnsuccess?.visibility = View.INVISIBLE
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        intent.putExtra("namef", name)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        binding?.tvRegUnsuccess?.visibility = View.VISIBLE
                    }
                }
            }
        }


        binding?.btnCancel?.setOnClickListener{
            cancelRegDisplay()
        }

    }

    fun cancelRegDisplay() {
        val customDialog = Dialog(this)
        val dialogBinding = CustomDialogBackMainRegBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)

        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            Toast.makeText(this, "Registration Canceled", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    fun login(view: View) {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)

    }
}




