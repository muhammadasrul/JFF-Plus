package com.asrul.jffplus.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.asrul.jffplus.R
import com.asrul.jffplus.databinding.ActivityMainBinding
import com.asrul.jffplus.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        binding.fabFav.setOnClickListener {
            val uri = Uri.parse("jffplus://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home_nav -> {
                    loadFragment(HomeFragment())
                }
                R.id.setting_nav -> {
                    loadFragment(SettingsFragment())
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_frame, fragment)
                .commit()
            return true
        }
        return false
    }
}