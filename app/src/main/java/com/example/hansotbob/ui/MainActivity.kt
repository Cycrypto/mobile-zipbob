package com.example.hansotbob.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.hansotbob.R
import com.example.hansotbob.fragment.RecyclerViewFragment
import com.github.florent37.materialviewpager.MaterialViewPager
import com.github.florent37.materialviewpager.header.HeaderDesign
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var materialViewPager: MaterialViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        materialViewPager = findViewById(R.id.materialViewPager)
        materialViewPager.viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return RecyclerViewFragment.newInstance()
            }

            override fun getCount(): Int {
                return 3
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return when(position) {
                    0 -> "TAB1"
                    1 -> "TAB2"
                    2 -> "TAB3"
                    else -> null
                }
            }
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Handle home click
                    true
                }
                R.id.nav_search -> {
                    // 테스트중
                    startActivity(Intent(this, ShareRegisterActivity::class.java))
                    true
                }
                R.id.nav_add -> {
                    // 테스트중
                    startActivity(Intent(this, MealkitDetailActivity::class.java))
                    true
                }
                R.id.nav_notifications -> {
                    // Handle notifications click
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, MyPageActivity::class.java))
                    true
                }
                else -> false
            }
        }


        materialViewPager.setMaterialViewPagerListener(object : MaterialViewPager.Listener {
            override fun getHeaderDesign(page: Int): HeaderDesign {
                return when (page) {
                    0 -> HeaderDesign.fromColorResAndUrl(
                        R.color.white,
                        "https://month.foodbank.co.kr/upload/article/20160607/20160607001001000001_1.jpg"
                    )
                    1 -> HeaderDesign.fromColorResAndUrl(
                        R.color.pink,
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRy4ucx_8RS2UwvjhsaHDKENwVd0UY-zj1WCA&s"
                    )
                    2 -> HeaderDesign.fromColorResAndUrl(
                        R.color.yellow,
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdvP1miovsdapMqJhOOfDl-oHtCdIHagst7Q&s"
                    )
                    else -> throw IllegalArgumentException("Page out of range")
                }
            }
        })
        materialViewPager.pagerTitleStrip.setViewPager(materialViewPager.viewPager)
    }


}



