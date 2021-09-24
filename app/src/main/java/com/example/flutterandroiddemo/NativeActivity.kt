package com.example.flutterandroiddemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.plugin.common.MethodChannel

/**
 *
 * Create by wp on 2021/9/24
 *
 * Description:
 *
 */
class NativeActivity :AppCompatActivity(){

    companion object{
        private const val To_Android_Channel = "com.example.flutterandroiddemo/plugin"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native)
        registerFlutterChannel()
    }

    private fun registerFlutterChannel() {

    }
}