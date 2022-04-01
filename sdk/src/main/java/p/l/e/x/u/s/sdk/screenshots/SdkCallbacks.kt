package p.l.e.x.u.s.sdk.screenshots

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

object SdkCallbacks : ActivityLifecycleCallbacks {

    init {
        /**
         * Load the 'sberapps_sdk' library on startup
         */
        System.loadLibrary("sdk")
    }

    /**
     * A native method that is implemented by the 'sberapps_sdk' native library
     */
    private external fun disableScreenshots(activity: Activity)

    override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
        disableScreenshots(activity)
        super.onActivityPreCreated(activity, savedInstanceState)
    }

    override fun onActivityCreated(
        activity: Activity, bundle: Bundle?
    ) = disableScreenshots(activity)

    override fun onActivityPostCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        disableScreenshots(activity)
        super.onActivityPostCreated(activity, savedInstanceState)
    }

    override fun onActivityPreStarted(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPreStarted(activity)
    }

    override fun onActivityStarted(activity: Activity) =
        disableScreenshots(activity)

    override fun onActivityPostStarted(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPostStarted(activity)
    }

    override fun onActivityPreResumed(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPreResumed(activity)
    }

    override fun onActivityResumed(activity: Activity) =
        disableScreenshots(activity)

    override fun onActivityPostResumed(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPostResumed(activity)
    }

    override fun onActivityPrePaused(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPrePaused(activity)
    }

    override fun onActivityPaused(activity: Activity) =
        disableScreenshots(activity)

    override fun onActivityPostPaused(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPostPaused(activity)
    }

    override fun onActivityPreStopped(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPreStopped(activity)
    }

    override fun onActivityStopped(activity: Activity) =
        disableScreenshots(activity)

    override fun onActivityPostStopped(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPostStopped(activity)
    }

    override fun onActivityPreSaveInstanceState(activity: Activity, outState: Bundle) {
        disableScreenshots(activity)
        super.onActivityPreSaveInstanceState(activity, outState)
    }

    override fun onActivitySaveInstanceState(
        activity: Activity, bundle: Bundle
    ) = disableScreenshots(activity)

    override fun onActivityPostSaveInstanceState(activity: Activity, outState: Bundle) {
        disableScreenshots(activity)
        super.onActivityPostSaveInstanceState(activity, outState)
    }

    override fun onActivityPreDestroyed(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPreDestroyed(activity)
    }

    override fun onActivityDestroyed(activity: Activity) =
        disableScreenshots(activity)

    override fun onActivityPostDestroyed(activity: Activity) {
        disableScreenshots(activity)
        super.onActivityPostDestroyed(activity)
    }
}