package p.l.e.x.u.s.security.app.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import p.l.e.x.u.s.security.app.base.navigation.Navigator
import javax.inject.Inject

abstract class BaseFragment : Fragment {
    protected val activityLauncher: BetterActivityResult<Intent, ActivityResult> =
        BetterActivityResult.registerActivityForResult(this)

    constructor() : super()
    constructor(@LayoutRes layoutId: Int) : super(layoutId)

    @Inject
    lateinit var navigator: Navigator
    open val isStatusBarWhite = true
    open var isAutoHideKeyboard = true
    private var progressAlert: AlertDialog? = null
    private var alreadyCreated = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isAutoHideKeyboard) {
            initAutoHideKeyboard(view)
        }
    }

    override fun onResume() {
        super.onResume()
        navigator.setNavController(findNavController())

        if (isStatusBarWhite) {
            mainActivity.colorStatusBar()
        } else {
            mainActivity.cleanStatusBar()
        }
    }

    protected val mainActivity: BaseActivity
        get() = requireActivity() as BaseActivity

    protected fun showLoseDataAlert() {

    }

    @Suppress("UNUSED_PARAMETER")
    protected fun showAskDeleteAlert(onPositive: () -> Unit) {

    }

    protected fun showCantSaveAlert() {

    }

    protected fun showProgressAlert() {

    }

    protected fun hideProgressAlert() {
        progressAlert?.dismiss()
    }

    open fun tryGoBack() {
        navigator.goBack()
    }

    protected fun isGoingBack(savedInstanceState: Bundle?): Boolean {
        return if (savedInstanceState == null && !this.alreadyCreated) {
            this.alreadyCreated = true
            false
        } else {
            if (savedInstanceState != null) {
                this.alreadyCreated = true
            }

            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initAutoHideKeyboard(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                initAutoHideKeyboard(innerView)
            }
        }
    }
}
