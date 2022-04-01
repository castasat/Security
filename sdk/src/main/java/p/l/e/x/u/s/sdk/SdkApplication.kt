package p.l.e.x.u.s.sdk

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import p.l.e.x.u.s.sdk.manager.SdkApplicationManager
import p.l.e.x.u.s.sdk.usecases.ICanManageSdkApplication
import p.l.e.x.u.s.security.BuildConfig

open class SdkApplication : Application(),
    ICanManageSdkApplication by SdkApplicationManager() {

    override fun onCreate() {
        super.onCreate()
        initialize(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        initialize(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        initialize(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        initialize(this)
    }

    override fun onTerminate() {
        clear(this)
        super.onTerminate()
    }

    companion object {
        fun log(message: String) {
            if (BuildConfig.DEBUG) {
                Log.d("p.l.e.x.u.s", message)
            }
        }
    }
}