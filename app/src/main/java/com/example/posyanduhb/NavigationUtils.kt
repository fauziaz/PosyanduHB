package com.example.posyanduhb

import android.app.Activity
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Small helper to wire the app's bottom navigation and center FAB across activities.
 * Safe to call on activities whose layouts don't include these views (it will no-op).
 */
fun setupBottomNavigation(activity: Activity) {
    try {
        val nav = activity.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        nav?.setOnItemSelectedListener { item ->
            android.util.Log.d("NavigationUtils", "Bottom nav selected id=${item.itemId}")
            when (item.itemId) {
                R.id.nav_reservasi -> {
                    // open ReservasiActivity
                    val i = Intent(activity, ReservasiActivity::class.java)
                    activity.startActivity(i)
                    true
                }
                R.id.nav_profile -> {
                    val i = Intent(activity, ProfileActivity::class.java)
                    activity.startActivity(i)
                    true
                }
                else -> false
            }
        }

        val fab = activity.findViewById<FloatingActionButton>(R.id.fab_beranda)
        fab?.setOnClickListener {
            val i = Intent(activity, HomeActivity::class.java)
            activity.startActivity(i)
        }
    } catch (e: Exception) {
        // safe no-op if views or Activity context not present
    }
}
