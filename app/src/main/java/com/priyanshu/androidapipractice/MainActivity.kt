package com.priyanshu.androidapipractice

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.priyanshu.androidapipractice.api.RetrofitInstance
import com.priyanshu.androidapipractice.app.DashboardActivity
import com.priyanshu.androidapipractice.auth.TermAndConditionActivity
import com.priyanshu.androidapipractice.models.LoginRequest
import com.priyanshu.androidapipractice.models.LoginResponse
import com.priyanshu.androidapipractice.ui.fragments.CustomLoaderDialog
import com.priyanshu.androidapipractice.util.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var username:EditText
    private lateinit var userPassword:EditText
    private lateinit var loginButton:Button
    private lateinit var termCondition: LinearLayout
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById<EditText>(R.id.emailEdit)
        userPassword = findViewById<EditText>(R.id.passwordEdit)
        loginButton = findViewById<Button>(R.id.signInbutton)
        termCondition = findViewById<LinearLayout>(R.id.terms)
        loginButton.setOnClickListener(this)
        termCondition.setOnClickListener(this)
        sessionManager = SessionManager(this)
        checkLogin()
    }

    private fun checkLogin(){
        val isLoggedIn: Boolean= sessionManager.isLoggedIn()
        if(isLoggedIn){
            val appintent = Intent(this@MainActivity,DashboardActivity::class.java)
            appintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(appintent)
            finish()
        }
    }








    override fun onClick(view: View) {
        when(view.id){
            R.id.signInbutton -> {
                applogin()
//                showTrackScreen()
            }
            R.id.terms ->{
                showTermView()
            }
            else ->{
                println("None of the above selected")
            }
        }
    }

    private fun showTermView(){
        val intent = Intent(this@MainActivity,TermAndConditionActivity::class.java)
        startActivity(intent)
    }

    private fun applogin(){
        if(!inputCheckEmpty()){
            showToast("Please do not leave fields empty")
            return
        }

        val email = username.text.toString().trim{it<=' '}
        val password = userPassword.text.toString().trim{it<=' '}
        val request = LoginRequest(email,password)


        RetrofitInstance.getApi().login(request).enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>){
                if(response.isSuccessful){
                    Log.d("API Success","Token ${response.body()?.accessToken}")
                    sessionManager.saveAuthToken(response.body()?.accessToken)
                    Log.d("Api details","${response.errorBody()}")
                    Log.i("Api Details 2:", response.message())

                    val trackIntent = Intent(this@MainActivity,DashboardActivity::class.java)
                    trackIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(trackIntent)
                    finish()

                    showToast("Login Success")
                }else{
                    Log.e("API_Error","Error ${response.code()}, Response Error Body ${response.errorBody()?.string()}")
                    showToast("Login Failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Api Failure","Request Failed: ${t.message}")
                Log.i("Api Info","Request Failed: ${t.message}")
                showToast("${t.message}")
            }
        })


    }

    private fun showTrackScreen(){



        val loader = CustomLoaderDialog()
        loader.show(supportFragmentManager,"Loader")
        Handler(Looper.getMainLooper()).postDelayed({
            loader.dismiss()
            val trackIntent = Intent(this@MainActivity,DashboardActivity::class.java)
            trackIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(trackIntent)
            finish()
        },3000)

    }

    private fun showToast(message:String){
        val toast = Toast.makeText(this,message,Toast.LENGTH_LONG)
        toast.show()
    }

    private fun inputCheckEmpty(): Boolean{
        return username.text.toString().trim{it<=' '}.isNotEmpty() && userPassword.text.toString().trim{it<=' '}.isNotEmpty()
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
