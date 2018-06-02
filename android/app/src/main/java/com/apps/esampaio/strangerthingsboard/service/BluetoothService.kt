package com.apps.esampaio.strangerthingsboard.service

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


/**
 * Created by eduar on 01/06/2018.
 */

class BluetoothService {
    var btAdapter:BluetoothAdapter? = null
    val context:Context

    private var socket: BluetoothSocket? = null
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null

    private var connected = false;


    constructor(context: Context){
        this.context = context
        this.btAdapter = BluetoothAdapter.getDefaultAdapter()
    }

    fun bluetoothIsEnabled():Boolean{
        return btAdapter != null
    }

    fun turnOnBluetooth(){
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)//        startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH);
        context.startActivity(enableBtIntent)

    }

    fun getParedDevices(): Set<BluetoothDevice> {
        if(btAdapter!=null) {
            val pairedDevices = btAdapter!!.getBondedDevices()
            return pairedDevices
        }else{
            return emptySet()
        }
    }


    @Throws(Exception::class)
    fun connect(bluetoothDevice: BluetoothDevice) {

        val uuid = bluetoothDevice.getUuids()[0].getUuid()

        socket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid)
        if( socket != null) {
            val socket = socket!!
            socket.connect()

            inputStream = socket.getInputStream()
            outputStream = socket.getOutputStream()

            connected = true
        }
    }

    @Throws(Exception::class)
    fun disconnect() {
        outputStream?.close()
        inputStream?.close()
        socket?.close()
        connected = false
    }

    fun isConnected(): Boolean {
        return connected
    }

    @Throws(Exception::class)
    fun send(data: ByteArray) {
        try {
            outputStream?.write(data)
        } catch (e: IOException) {
            connected = false
        }
    }

    @Throws(Exception::class)
    fun receive(): ByteArray? {
        try {
            val buffer = ByteArray(8)
            for (i in buffer.indices)
                buffer[i] = '\u0000'.toByte()
            val bytesRead = inputStream?.read(buffer)
            return buffer
        } catch (e: IOException) {
            connected = false
            return null
        }

    }
}
