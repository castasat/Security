package p.l.e.x.u.s.security.app.ui.base

import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    fun colorStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.parseColor("#80FFFFFF")
    }

    fun cleanStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.parseColor("#80FFFFFF")
    }
}