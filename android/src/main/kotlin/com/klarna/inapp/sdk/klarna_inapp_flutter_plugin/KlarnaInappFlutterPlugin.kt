package com.klarna.inapp.sdk.klarna_inapp_flutter_plugin

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry.Registrar

/** KlarnaInappFlutterPlugin */
public class KlarnaInappFlutterPlugin : FlutterPlugin, ActivityAware {
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        MethodCallHandlerManager.handlerMap.forEach {
            val channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, it.key)
            channel.setMethodCallHandler(it.value)
        }
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            MethodCallHandlerManager.handlerMap.forEach {
                val channel = MethodChannel(registrar.messenger(), it.key)
                channel.setMethodCallHandler(it.value)
            }

            PluginContext.activity = registrar.activity()
            PluginContext.context = registrar.context()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {

    }

    override fun onDetachedFromActivity() {
        PluginContext.activity = null
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {

    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        PluginContext.activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {

    }

}
