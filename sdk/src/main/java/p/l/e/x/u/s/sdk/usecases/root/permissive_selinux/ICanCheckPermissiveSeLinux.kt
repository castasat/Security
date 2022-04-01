package p.l.e.x.u.s.sdk.usecases.root.permissive_selinux

interface ICanCheckPermissiveSeLinux {
    /**
     * Check if android system has permissive SeLinux flag
     */
    fun checkPermissiveSeLinux(): Boolean
}