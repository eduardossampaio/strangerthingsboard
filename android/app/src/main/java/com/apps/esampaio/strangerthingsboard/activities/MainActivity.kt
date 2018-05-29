package com.apps.esampaio.strangerthingsboard.activities

import android.graphics.Typeface
import android.os.Bundle
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.apps.esampaio.strangerthingsboard.R
import com.apps.esampaio.strangerthingsboard.activities.view.custom.WallLetter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {
    var letterViews = emptyMap<String, WallLetter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messageToSend.setTypeface(Typeface.createFromAsset(getAssets(), "font/benguiat_bold.ttf"))

        letterViews = mapOf<String, WallLetter>(
                "a" to letter_a,
                "b" to letter_b,
                "c" to letter_c,
                "d" to letter_d,
                "e" to letter_e,
                "f" to letter_f,
                "g" to letter_g,
                "h" to letter_h,
                "i" to letter_i,
                "j" to letter_j,
                "k" to letter_k,
                "l" to letter_l,
                "m" to letter_m,
                "n" to letter_n,
                "o" to letter_o,
                "p" to letter_p,
                "q" to letter_q,
                "r" to letter_r,
                "s" to letter_s,
                "t" to letter_t,
                "u" to letter_u,
                "v" to letter_v,
                "w" to letter_w,
                "x" to letter_x,
                "y" to letter_y,
                "z" to letter_z
        );
    }

    fun letterClicked(view: View) {
//        if ( view is TextView) {
//            val animObj = AlphaAnimation(0f, 1f);
//            animObj.duration = 200
//            messageToSend.startAnimation(animObj)
//            messageToSend.append(view.text)
//        }

        if (view is WallLetter) {
            messageToSend.append(view.getLetter())
        }
    }


    fun sendButtonClicked(view: View) {
        send(messageToSend.text.toString())
        messageToSend.text = ""
    }

    private fun send(message: String) {
        val delayTime = 1000L
        doAsync {
            for (letter in message.toCharArray()) {
                uiThread {
                    letterViews[letter.toString()]?.blinkLed(delayTime)
                }
                Thread.sleep(delayTime)
            }
        }

    }
}
