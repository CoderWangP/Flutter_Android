package com.example.flutterandroiddemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    private val mFlutterEngine by lazy {
        FlutterEngine(this)
    }

    companion object {
        private const val CHANNEL = "com.example.flutterandroiddemo/channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerEngine()
    }

    private fun registerEngine() {
        // Configure an initial route.
        mFlutterEngine.navigationChannel.setInitialRoute("/page_2")
        // Start executing Dart code to pre-warm the FlutterEngine.
        FlutterEngineCache.getInstance().put("flutter_engine", mFlutterEngine)
        mFlutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        MethodChannel(
            mFlutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            if ("forward2Native" == call.method) {
                val message = call.arguments as String
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                result.success("success")
                startActivity(Intent(this, NativeActivity::class.java))
            }
        }
    }

    fun forward2Flutter(view: View) {
        /*startActivity(FlutterActivity
            .createDefaultIntent(this))*/
/*        startActivity(
            FlutterActivity
                .withNewEngine()
                .initialRoute("/page_2")
                .build(this)
        )*/
        startActivity(
            FlutterActivity
                .withCachedEngine("flutter_engine")
                .build(this)
        )
    }
}