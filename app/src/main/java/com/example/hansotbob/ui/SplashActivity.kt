package com.example.hansotbob.ui

import android.content.Intent
import android.graphics.Typeface
import com.daimajia.androidanimations.library.Techniques
import com.example.hansotbob.R
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash


class SplashView : AwesomeSplash() {
    override fun initSplash(configSplash: ConfigSplash) {
        // Customize Circular Reveal
        configSplash.backgroundColor = R.color.yellow // any color you want from colors.xml
        configSplash.animCircularRevealDuration = 2000 // int ms
        configSplash.revealFlagX = Flags.REVEAL_RIGHT // or Flags.REVEAL_LEFT
        configSplash.revealFlagY = Flags.REVEAL_BOTTOM // or Flags.REVEAL_TOP

        // Customize Logo
        configSplash.logoSplash = R.drawable.logo // or any other drawable
        configSplash.animLogoSplashDuration = 1800 // int ms
        configSplash.animLogoSplashTechnique = Techniques.FadeIn // choose one from Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        // Customize Title
        configSplash.titleSplash = "한솥밥"
        configSplash.titleTextColor = R.color.white
        configSplash.titleTextSize = 50f // float value
        configSplash.animTitleDuration = 2000
        configSplash.animTitleTechnique = Techniques.FadeIn
        configSplash.titleFont = "font/WandohopeB.ttf" // provide a font in assets/fonts
    }

    override fun animationsFinished() {
        // Redirect to another activity after the splash screen
        startActivity(Intent(this@SplashView, OnboardingActivity::class.java))
        finish()
    }
}