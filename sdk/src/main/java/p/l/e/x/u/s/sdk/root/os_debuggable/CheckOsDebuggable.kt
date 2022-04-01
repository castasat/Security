package p.l.e.x.u.s.sdk.root.os_debuggable

import p.l.e.x.u.s.sdk.usecases.root.os_debuggable.ICanCheckIfSystemIsDebuggable


class CheckOsDebuggable : ICanCheckIfSystemIsDebuggable {
    override fun checkIfSystemIsDebuggable(): Boolean =
        checkDebuggable() ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun checkDebuggable(): Boolean?
    }
}