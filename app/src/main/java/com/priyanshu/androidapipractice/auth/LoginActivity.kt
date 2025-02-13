package com.priyanshu.androidapipractice.auth

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.priyanshu.androidapipractice.R


class LoginActivity : AppCompatActivity(){

    lateinit var usernameInput: EditText
    lateinit var usernamePassword: EditText
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.emailEdit)
        usernamePassword = findViewById(R.id.passwordEdit)
        loginButton = findViewById(R.id.signInbutton)

        loginButton.setOnClickListener(){
            val username = usernameInput.text.toString()
            val password = usernamePassword.text.toString()

            Log.i("Login Crendentials","Username $usernameInput $usernamePassword",)
        }
    }


    private fun showDialog(message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
        builder.setMessage(message)

        builder.setPositiveButton("Okay"){
            dialog, _->
            dialog.dismiss()
        }

        builder.setNegativeButton("No"){
            dialog, _-> dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}