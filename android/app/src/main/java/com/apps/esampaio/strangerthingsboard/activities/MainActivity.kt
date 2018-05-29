package com.apps.esampaio.strangerthingsboard.activities

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.apps.esampaio.strangerthingsboard.R
import kotlinx.android.synthetic.main.activity_main.*
import android.opengl.ETC1.getWidth
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import com.apps.esampaio.strangerthingsboard.activities.view.custom.WallLetter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messageToSend.setTypeface(Typeface.createFromAsset(getAssets(),"font/benguiat_bold.ttf"))
    }

    fun letterClicked(view:View){
//        if ( view is TextView) {
//            val animObj = AlphaAnimation(0f, 1f);
//            animObj.duration = 200
//            messageToSend.startAnimation(animObj)
//            messageToSend.append(view.text)
//        }

        if ( view is WallLetter){
            messageToSend.append(view.getLetter())
            view.blinkLed(1000)
        }
    }


    fun sendButtonClicked(view: View){
        send(messageToSend.text.toString())
        messageToSend.text=""
    }

    private fun send(toString: String) {
        
    }
}
