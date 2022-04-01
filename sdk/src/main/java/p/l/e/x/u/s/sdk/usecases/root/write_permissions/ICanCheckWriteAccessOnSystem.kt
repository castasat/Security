package p.l.e.x.u.s.sdk.usecases.root.write_permissions

interface ICanCheckWriteAccessOnSystem {
    /**
     * Check if system paths have write access
     **/
    fun checkWriteAccessOnSystem(): Boolean
}