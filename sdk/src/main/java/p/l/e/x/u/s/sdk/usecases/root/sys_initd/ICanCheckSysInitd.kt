package p.l.e.x.u.s.sdk.usecases.root.sys_initd

interface ICanCheckSysInitd {
    /**
     * Checks system properties to find sys initd
     */
    fun checkSysInitd(): Boolean
}