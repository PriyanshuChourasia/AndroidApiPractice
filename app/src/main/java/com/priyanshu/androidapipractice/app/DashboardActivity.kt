package com.priyanshu.androidapipractice.app

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.priyanshu.androidapipractice.R
import com.priyanshu.androidapipractice.ui.fragments.HistoryFragment
import com.priyanshu.androidapipractice.ui.fragments.NotificationFragment
import com.priyanshu.androidapipractice.ui.fragments.ReportFragment
import com.priyanshu.androidapipractice.ui.fragments.TrackFragment

class DashboardActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var navigationBarView: NavigationBarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.show()
        supportActionBar?.apply {
            title="Track"
//            setDisplayHomeAsUpEnabled(true)
        }

        navigationBarView = findViewById(R.id.bottom_navigation)
        navigationBarView.setOnItemSelectedListener(this)
        loadFragment(TrackFragment())
    }

    override fun onSupportNavigateUp():Boolean{
        finish()
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when(item.itemId){
            R.id.track_nav->{
                fragment = TrackFragment()
            }
            R.id.history_nav->{
                fragment = HistoryFragment()
            }
            R.id.report_nav->{
                fragment = ReportFragment()
            }
            R.id.notification_nav->{
                fragment = NotificationFragment()
            }
        }

        if(fragment != null){
            loadFragment(fragment)
        }

        return true
    }

    private fun loadFragment(fragment: Fragment){
//         to attach fragments
        supportFragmentManager.beginTransaction().replace(R.id.relativeLayout,fragment).commit()
    }
}