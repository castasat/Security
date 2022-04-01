package p.l.e.x.u.s.sdk.usecases.root.accessed.superuser

interface ICanCheckSuperuser {
    /**
     * Checks if Superuser is installed
     */
    fun checkSuperuser(): Boolean
}