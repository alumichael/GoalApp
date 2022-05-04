package com.michealalu.goalapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import com.fbn.fbnquest.BaseActivity
import com.michealalu.goalapp.R
import com.michealalu.goalapp.StartNewActivityWithFlag
import com.michealalu.goalapp.databinding.ActivitySplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var bottomAnim: Animation
    private lateinit var blinkAnim: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)

        //Animations
        blinkAnim = AnimationUtils.loadAnimation(this, R.anim.blink)

        binding.goalAppLogo.animation = blinkAnim

        lifecycleScope.launch{
            delay(5000)
            StartNewActivityWithFlag(HomeActivity::class.java)
        }
    }
}