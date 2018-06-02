package com.apps.esampaio.strangerthingsboard.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.apps.esampaio.strangerthingsboard.R
import kotlinx.android.synthetic.main.activity_splash.*
import com.whygraphics.gifview.gif.GIFView
import android.widget.Toast


class SplashActivity : AppCompatActivity(), GIFView.OnSettingGifListener {
    override fun onSuccess(p0: GIFView?, p1: java.lang.Exception?) {
        Handler().postDelayed({
            splash_image.stop()
            goToMainActivity()
        }, splash_image.gifDuration.toLong() * 1000);
    }

    override fun onFailure(p0: GIFView?, p1: java.lang.Exception?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            splash_image.setGifResource("asset:splash")
            splash_image.setOnSettingGifListener(this)
        },1000)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
