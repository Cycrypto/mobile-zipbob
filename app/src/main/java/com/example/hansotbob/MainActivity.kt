package com.example.hansotbob

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import com.example.hansotbob.fragment.RecyclerViewFragment
import com.github.florent37.materialviewpager.MaterialViewPager
import com.github.florent37.materialviewpager.header.HeaderDesign



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



