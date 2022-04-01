package p.l.e.x.u.s.sdk.usecases.root.os_insecure

interface ICanCheckIfOsInsecure {
    /**
     * Check system properties to find if OS_SECURE property has inappropriate value
     */
    fun checkIfOsInsecure(): Boolean
}