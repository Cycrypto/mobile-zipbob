package com.example.hansotbob.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.hansotbob.MainActivity
import com.example.hansotbob.R
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.PaperOnboardingPage
class OnboardingActivity : AppCompatActivity() {

        private lateinit var container: FrameLayout
        private lateinit var revealView: View
        private var currentFragment: PaperOnboardingFragment? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_onboarding)

            container = findViewById(R.id.container)
            revealView = findViewById(R.id.reveal_view)

            val page1 = PaperOnboardingPage(
                "집밥 공유",
                "지역 공동체 사람들과 집밥을 공유해보세요",
                Color.parseColor("#FFFFFF"),
                R.drawable.img_share_meal,
                R.drawable.ic_rice
            )

            val page2 = PaperOnboardingPage(
                "재료 공유",
                "혼자 소비하기 많은 재료들을 공동으로 구매 또는 나눠보세요.",
                Color.parseColor("#FFFFFF"),
                R.drawable.img_share_ingredient,
                R.drawable.ic_rice
            )

            val page3 = PaperOnboardingPage(
                "레시피 공유",
                "나만의 레시피를 공동체와 함께 공유해보세요.",
                Color.parseColor("#FFFFFF"),
                R.drawable.img_share_recipies,
                R.drawable.ic_next_arrow
            )

            val elements = arrayListOf(page1, page2, page3)

            val onBoardingFragment = PaperOnboardingFragment.newInstance(elements)
            currentFragment = onBoardingFragment

//        onBoardingFragment.setOnChangeListener(PaperOnboardingOnChangeListener { position, _ ->
//            val color = Color.parseColor("#FFA726")
//            performCircularReveal(onBoardingFragment, color)
//        })

            onBoardingFragment.setOnRightOutListener {
                // When the user swipes out from the last page, start MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finish the onboarding activity so it can't be returned to
            }

            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.container, onBoardingFragment)
            fragmentTransaction.commit()
        }
}

// 에니메이션 관련 주석 (필요시 사용)

//    private fun performCircularReveal(fragment: PaperOnboardingFragment, color: Int) {
//        revealView.setBackgroundColor(color)
//
//        val cx = container.width / 2
//        val cy = container.height / 2
//
//        val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
//        val anim = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0f, finalRadius)
//
//        revealView.visibility = View.VISIBLE
//        anim.start()
//
//        anim.addListener(object : Animator.AnimatorListener {
//            override fun onAnimationStart(animation: Animator) {
//                currentFragment?.let {
//                    supportFragmentManager.beginTransaction().remove(it).commit()
//                }
//            }
//
//            override fun onAnimationEnd(animation: Animator) {
//                val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//                fragmentTransaction.replace(R.id.container, fragment)
//                fragmentTransaction.commit()
//
//                revealView.visibility = View.INVISIBLE
//                currentFragment = fragment
//            }
//
//            override fun onAnimationCancel(animation: Animator) {
//                revealView.visibility = View.INVISIBLE
//            }
//
//            override fun onAnimationRepeat(animation: Animator) {}
//        })
//    }
