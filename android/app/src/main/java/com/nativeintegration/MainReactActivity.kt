package com.nativeintegration

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ProgressBar
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.ReactRootView
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
import com.facebook.react.defaults.DefaultReactActivityDelegate

abstract class MainReactActivity : ReactActivity() {

    private lateinit var progressBar: ProgressBar

    /**
     * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
     * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
     */

    @SuppressLint("MissingInflatedId", "VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_react_root_view)
        progressBar = findViewById(R.id.pb_react_root_view_loading)
    }

    override fun createReactActivityDelegate(): ReactActivityDelegate {
        return object : DefaultReactActivityDelegate(this, mainComponentName.toString(), fabricEnabled) {
            override fun createRootView(): ReactRootView {
                return object : ReactRootView(this@MainReactActivity) {
                    override fun onAttachedToWindow() {
                        super.onAttachedToWindow()

                        progressBar.visibility = GONE
                        this.visibility = VISIBLE
                    }
                }
            }
        }
    }
}
