package com.apps.esampaio.strangerthingsboard.activities.view.custom

import com.apps.esampaio.strangerthingsboard.R

/**
 * Created by eduar on 28/05/2018.
 */

internal enum class WallLetterLedColor(val resourceId:Int) {
    OFF(R.drawable.led_off),
    RED(R.drawable.led_red),
    GREEN(R.drawable.led_green),
    BLUE(R.drawable.led_blue);

   companion object {
       fun ordinal(ordinal: Int): WallLetterLedColor {
           return when (ordinal) {
               1 -> WallLetterLedColor.RED
               2 -> WallLetterLedColor.GREEN
               3 -> WallLetterLedColor.BLUE
               else -> WallLetterLedColor.OFF
           }
       }
   }
}
