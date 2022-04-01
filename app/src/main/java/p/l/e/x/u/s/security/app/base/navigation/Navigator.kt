package p.l.e.x.u.s.security.app.base.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import p.l.e.x.u.s.security.R
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("unused")
@Singleton
class Navigator @Inject constructor() {
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    fun getNavController(): NavController? {
        return navController
    }

    fun removeNavController(navController: NavController) {
        if (this.navController !== navController) return
        this.navController = null
    }

    fun open(screen: Screen) {
        try {
            navController?.navigate(screen.direction())
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
    }

    fun open(deepLink: Uri, slideUp: Boolean = false) {
        val navOptions = if (slideUp) {
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_up)
                .setExitAnim(R.anim.nudge_up)
                .setPopExitAnim(R.anim.slide_down)
                .setPopEnterAnim(R.anim.nudge_down)
                .build()
        } else {
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in)
                .setExitAnim(R.anim.slide_left)
                .setPopExitAnim(R.anim.slide_out)
                .setPopEnterAnim(R.anim.slide_right)
                .build()
        }
        navController?.navigate(deepLink, navOptions)
    }

    fun goBack() {
        if (navController?.previousBackStackEntry?.destination != null) {
            navController?.popBackStack()
        }
    }

    fun goToRoot() {
        navController?.let {
            it.navigate(it.graph.startDestinationId)
        }
    }
}
