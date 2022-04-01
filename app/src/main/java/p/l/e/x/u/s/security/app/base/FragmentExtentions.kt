package p.l.e.x.u.s.security.app.base

import android.app.Activity.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import androidx.core.view.postDelayed

fun BaseFragment.showKeyboard(view: View) {
    view.postDelayed(100) {
        view.requestFocus()
        (requireContext().getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.showSoftInput(view, 0)
    }
}

fun BaseFragment.hideKeyboard() {
    val inputManager = requireActivity()
        .getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager

    val currentFocusedView = requireActivity().currentFocus
    if (currentFocusedView != null) {
        inputManager?.hideSoftInputFromWindow(currentFocusedView.windowToken, HIDE_NOT_ALWAYS)
    }
}