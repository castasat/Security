package p.l.e.x.u.s.sdk.root.os_insecure

import p.l.e.x.u.s.sdk.usecases.root.os_insecure.ICanCheckIfOsInsecure

class OsInsecure : ICanCheckIfOsInsecure {
    override fun checkIfOsInsecure(): Boolean =
        osInsecure() ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun osInsecure(): Boolean?
    }
}