package p.l.e.x.u.s.sdk.usecases.root.os_debuggable

interface ICanCheckIfSystemIsDebuggable {
    /**
     * Check system properties to find if OS is debuggable
     */
    fun checkIfSystemIsDebuggable(): Boolean
}