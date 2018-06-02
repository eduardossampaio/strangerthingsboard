package com.apps.esampaio.strangerthingsboard.activities

import android.graphics.Typeface
import android.os.Bundle
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.apps.esampaio.strangerthingsboard.R
import com.apps.esampaio.strangerthingsboard.activities.view.custom.WallLetter
import com.apps.esampaio.strangerthingsboard.service.BluetoothService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.letters.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {
    var letterViews = emptyMap<String, WallLetter>()
    var bluetoothService:BluetoothService?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        messageToSend.setTypeface(Typeface.createFromAsset(getAssets(), "font/benguiat_bold.ttf"))
        this.bluetoothService = BluetoothService(this)

        letterViews = mapOf<String, WallLetter>(
                "a" to letter_a, "b" to letter_b, "c" to letter_c, "d" to letter_d,
                "e" to letter_e, "f" to letter_f, "g" to letter_g, "h" to letter_h,
                "i" to letter_i, "j" to letter_j, "k" to letter_k, "l" to letter_l,
                "m" to letter_m, "n" to letter_n, "o" to letter_o, "p" to letter_p,
                "q" to letter_q, "r" to letter_r, "s" to letter_s, "t" to letter_t,
                "u" to letter_u, "v" to letter_v, "w" to letter_w, "x" to letter_x,
                "y" to letter_y, "z" to letter_z);
    }

    override fun onResume() {
        super.onResume()
        connectToUpsideDown()
    }

    fun connectToUpsideDown(){
        if(bluetoothService != null) {
            val bluetoothService = bluetoothService!!
            if( ! bluetoothService.isConnected()) {
                val paredDevices = bluetoothService.getParedDevices()
                for (device in paredDevices) {
                    if (device.name.equals("HC-05")) {
                        bluetoothService.connect(device)
                        Toast.makeText(this, "pared to upside down", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    fun letterClicked(view: View) {
        if (view is WallLetter) {
            messageToSend.append(view.getLetter())
        }
    }

    fun sendButtonClicked(view: View) {
        send(messageToSend.text.toString())
        messageToSend.text = ""
    }

    fun clearButtonClicked(view: View){
        messageToSend.text = ""
    }

    private fun send(message: String) {
        val delayTime = 1000L
        doAsync {
            for (letter in message.toCharArray()) {
                uiThread {
                    letterViews[letter.toString()]?.blinkLed(delayTime)
                    val bluetoothService = bluetoothService!!
                    if (bluetoothService.isConnected()) {
                        val messageToSend = ByteArray(1);
                        messageToSend[0] = letter.toByte();
                        bluetoothService.send(messageToSend)
                    }
                }
                Thread.sleep(delayTime)
            }
        }
    }
}
