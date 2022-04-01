package p.l.e.x.u.s.sdk.root.sys_initd

import p.l.e.x.u.s.sdk.usecases.root.sys_initd.ICanCheckSysInitd

class SysInitd : ICanCheckSysInitd {
    override fun checkSysInitd(): Boolean =
        sysInitd() ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun sysInitd(): Boolean?
    }
}