package com.priyanshu.androidapipractice.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.priyanshu.androidapipractice.R
import android.view.View

class LoginActivity : AppCompatActivity(), View.OnClickListener, View.OnKeyListener, View.OnFocusChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
    }

    override fun onCLick(p0: View?){

    }
}