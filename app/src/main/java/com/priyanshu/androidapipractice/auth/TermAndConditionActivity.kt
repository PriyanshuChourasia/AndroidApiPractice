package com.priyanshu.androidapipractice.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.priyanshu.androidapipractice.R

class TermAndConditionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_term_and_condition)
        supportActionBar?.apply {
            title="Terms and Condition"
            setDisplayHomeAsUpEnabled(true)

        }
        supportActionBar?.show()
    }

    override fun onSupportNavigateUp(): Boolean{
        finish()
        return true
    }
}