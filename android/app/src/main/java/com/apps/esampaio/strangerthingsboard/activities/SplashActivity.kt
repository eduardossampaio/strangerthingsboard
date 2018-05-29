package com.apps.esampaio.strangerthingsboard.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.apps.esampaio.strangerthingsboard.R
import com.medialablk.easygifview.EasyGifView
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash_image.gifFromResource = R.drawable.splash //Your own GIF file from Resources
        Handler().postDelayed({
            goToMainActivity()
        },splash_image.gifDuration);

    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
