package p.l.e.x.u.s.security.app.base.navigation

import androidx.navigation.NavDirections

fun interface Screen {
    fun direction(): NavDirections
}