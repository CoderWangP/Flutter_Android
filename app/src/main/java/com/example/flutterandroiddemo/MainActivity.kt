package com.example.flutterandroiddemo
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : AppCompatActivity() {

    companion object{
        private const val CHANNEL = "com.example.flutterandroiddemo/channel"
    }

    private var mFlutterEngine :FlutterEngine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        registerFlutterChannel()
    }

    fun forward2Flutter(view: View) {
        /*startActivity(FlutterActivity
            .createDefaultIntent(this))*/

        startActivity(FlutterActivity
            .withNewEngine()
            .initialRoute("/page_2")
            .build(this))
    }


    private fun registerFlutterChannel() {
        mFlutterEngine = FlutterEngine(this)
        GeneratedPluginRegistrant.registerWith(mFlutterEngine!!)
        MethodChannel(mFlutterEngine?.dartExecutor, CHANNEL)
            .setMethodCallHandler { call, result ->
                val method = call.method
                if("withParams" == method){
                    startActivity(Intent(this@MainActivity,NativeActivity::class.java))
                }
            }
    }
}