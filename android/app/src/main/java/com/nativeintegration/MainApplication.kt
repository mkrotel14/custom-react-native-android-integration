package com.nativeintegration

import android.app.Application
import com.facebook.hermes.reactexecutor.HermesExecutorFactory
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.JavaScriptExecutorFactory
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.load
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost
import com.facebook.react.defaults.DefaultReactNativeHost
import com.facebook.soloader.SoLoader
import com.swmansion.gesturehandler.RNGestureHandlerPackage
import com.swmansion.reanimated.ReanimatedPackage

class MainApplication : Application(), ReactApplication {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
          load()
        }
    }

    override val reactNativeHost: DefaultReactNativeHost = object : DefaultReactNativeHost(this) {
        override fun getJSMainModuleName() = "index"
        override fun getBundleAssetName() = "index.android.bundle"
        override fun getUseDeveloperSupport(): Boolean = BuildConfig.DEBUG

        override fun getJavaScriptExecutorFactory() = HermesExecutorFactory()

        override val isNewArchEnabled: Boolean = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
        override val isHermesEnabled: Boolean = BuildConfig.IS_HERMES_ENABLED

        override fun getPackages(): List<ReactPackage> = PackageList(this).packages.apply {
//            RNGestureHandlerPackage()
//            ReanimatedPackage()
        }

    }

    override val reactHost: ReactHost
        get() = getDefaultReactHost(this.applicationContext, reactNativeHost)
}
