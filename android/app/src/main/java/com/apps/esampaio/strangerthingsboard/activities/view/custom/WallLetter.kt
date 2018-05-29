package com.apps.esampaio.strangerthingsboard.activities.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.apps.esampaio.strangerthingsboard.R
import android.content.res.TypedArray
import android.text.TextUtils
import android.widget.ImageView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.switch
import org.jetbrains.anko.uiThread
import java.util.logging.Handler


/**
 * Created by eduar on 28/05/2018.
 */
class WallLetter : LinearLayout {
    private var letterImageView: ImageView? = null
    private var ledImageView: ImageView? = null

    private var letter: Char? = null
    private var ledColor = WallLetterLedColor.OFF;

    constructor(context: Context?) : super(context) {
        buildView();
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        readAttr(attrs)
        buildView();
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        readAttr(attrs)
        buildView();
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        readAttr(attrs)
        buildView();
    }

    private fun buildView() {
        val inflatedView = LayoutInflater.from(context).inflate(R.layout.wall_letter, null, false)
        letterImageView = inflatedView.findViewById<ImageView>(R.id.letter_image_view)
        ledImageView = inflatedView.findViewById<ImageView>(R.id.led_image_view)
        gravity = Gravity.CENTER_HORIZONTAL
        addView(inflatedView)
        if (letter != null) {
            setLetter(letter!!)
        }

    }

    private fun readAttr(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.WallLetter, 0, 0)
        try {
            val textFromAttrs = ta.getText(R.styleable.WallLetter_letter)
            val ledColorFromAttrs = ta.getInt(R.styleable.WallLetter_led_color,0)
            this.ledColor = WallLetterLedColor.ordinal(ledColorFromAttrs)
            if (!TextUtils.isEmpty(textFromAttrs)) {
                this.letter = textFromAttrs!!.get(0)
            }
        } finally {
            ta.recycle()
        }
    }

    private fun setLetter(letter: Char) {
        this.letter = letter
        if(letter != null) {
            val imageIdRes = getImageResourceIdFromLetter(this.letter!!);
            if(imageIdRes!=0) {
                letterImageView?.setImageResource(imageIdRes)
            }
        }
    }

    fun getLetter():String{
        return ""+this.letter
    }
    fun ledOn(){
        ledImageView?.setImageResource(ledColor.resourceId)
    }
    fun ledOff(){
        ledImageView?.setImageResource(R.drawable.led_off)
    }
    fun blinkLed(time:Long){
        ledOn()
        handler.postDelayed({
            ledOff()
        },time)
    }
    private fun getImageResourceIdFromLetter(letter: Char):Int{

        return when(letter.toLowerCase()){
            'a' -> R.drawable.letter_a
            'b' -> R.drawable.letter_b
            'c' -> R.drawable.letter_c
            'd' -> R.drawable.letter_d
            'e' -> R.drawable.letter_e
            'f' -> R.drawable.letter_f
            'g' -> R.drawable.letter_g
            'h' -> R.drawable.letter_h
            'i' -> R.drawable.letter_i
            'j' -> R.drawable.letter_j
            'k' -> R.drawable.letter_k
            'l' -> R.drawable.letter_l
            'm' -> R.drawable.letter_m
            'n' -> R.drawable.letter_n
            'o' -> R.drawable.letter_o
            'p' -> R.drawable.letter_p
            'q' -> R.drawable.letter_q
            'r' -> R.drawable.letter_r
            's' -> R.drawable.letter_s
            't' -> R.drawable.letter_t
            'u' -> R.drawable.letter_u
            'v' -> R.drawable.letter_v
            'w' -> R.drawable.letter_w
            'x' -> R.drawable.letter_x
            'y' -> R.drawable.letter_y
            'z' -> R.drawable.letter_z
            else -> 0;
        }

    }
}