package p.l.e.x.u.s.sdk.usecases.root.accessed.xposed

interface ICanCheckXposed {
    /**
     * Checks if Xposed is installed
     */
    fun checkXposed(): Boolean
}