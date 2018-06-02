package com.apps.esampaio.strangerthingsboard.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.apps.esampaio.strangerthingsboard.Constants
import com.apps.esampaio.strangerthingsboard.R
import com.apps.esampaio.strangerthingsboard.activities.view.custom.WallLetter
import com.apps.esampaio.strangerthingsboard.service.BluetoothService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.letters.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {
    var letterViews = emptyMap<String, WallLetter>()
    var bluetoothService: BluetoothService = BluetoothService();

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
        bluetoothIconOff()
    }

    override fun onResume() {
        super.onResume()
        checkAndConnect()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == BluetoothService.ENABLE_BLLUETOOTH_RESULT_CODE && resultCode == Activity.RESULT_OK){
            connectToUpsideDown()
        }
    }

    private fun checkAndConnect(){
        doAsync {
            if (!bluetoothService.bluetoothIsEnabled()) {
                bluetoothService.turnOnBluetooth()
            } else if (!bluetoothService.isConnected()) {
                connectToUpsideDown()
            }else{
                bluetoothIconOn()
            }
        }
    }

    fun connectToUpsideDown() {
        if (!bluetoothService.isConnected()) {
            val paredDevices = bluetoothService.getParedDevices()
            for (device in paredDevices) {
                if (device.name.equals(Constants.BLUETOOTH_MODULE_NAME)) {
                    try {
                        bluetoothService.connect(device)
                        runOnUiThread {
                            Toast.makeText(this, "Conectado ao mundo invertido", Toast.LENGTH_SHORT).show()
                            bluetoothIconOn()
                        }
                    }catch (e:Exception){
                        runOnUiThread {
                            Toast.makeText(this, "O portal pro mundo invertido está fechado", Toast.LENGTH_SHORT).show()
                            bluetoothIconOff()
                        }
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

    fun clearButtonClicked(view: View) {
        messageToSend.text = ""
    }

    private fun send(message: String) {
        val delayTime = Constants.DELAY_TIME

        doAsync {
            for (letter in message.toCharArray()) {
                uiThread {
                    letterViews[letter.toString()]?.blinkLed(delayTime)
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

    private fun bluetoothIconOn(){
        runOnUiThread {
            bluetooth_icon.setImageResource(R.drawable.ic_bluetooth_on)
        }
    }

    private fun bluetoothIconOff(){
        runOnUiThread {
            bluetooth_icon.setImageResource(R.drawable.ic_bluetooth_off)
        }
    }
}
