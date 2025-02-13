package com.priyanshu.androidapipractice

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity(),View.OnClickListener {

    private lateinit var username:EditText
    private lateinit var userPassword:EditText
    private lateinit var loginButton:Button
    private lateinit var termCondition: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById<EditText>(R.id.emailEdit)
        userPassword = findViewById<EditText>(R.id.passwordEdit)
        loginButton = findViewById<Button>(R.id.signInbutton)
        termCondition = findViewById<LinearLayout>(R.id.terms)
        loginButton.setOnClickListener(this)
        termCondition.setOnClickListener(this)
    }




    private fun inputCheckEmpty(): Boolean{
        return username.text.toString().trim{it<=' '}.isNotEmpty() && userPassword.text.toString().trim{it<=' '}.isNotEmpty()
    }




    override fun onClick(view: View) {
        when(view.id){
            R.id.signInbutton -> {
                showToast("Login")
            }
            R.id.terms ->{
                showToast("Terms and Condition")
            }
            else ->{
                println("None of the above selected")
            }
        }
    }

    private fun showToast(message:String){
        val toast = Toast.makeText(this,message,Toast.LENGTH_LONG)
        toast.show()
    }


    private fun showLoginDialog(message:String){
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
