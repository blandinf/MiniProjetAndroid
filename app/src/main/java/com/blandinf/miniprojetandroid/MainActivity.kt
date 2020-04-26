package com.blandinf.miniprojetandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.blandinf.miniprojetandroid.fragments.HomeFragment
import com.blandinf.miniprojetandroid.fragments.ModeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        change(HomeFragment())
    }
}

fun FragmentActivity.change(fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {

        replace(R.id.container, fragment)

        addToBackStack(null)
    }.commit()
}
