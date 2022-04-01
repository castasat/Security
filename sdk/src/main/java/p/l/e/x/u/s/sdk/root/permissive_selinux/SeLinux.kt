package p.l.e.x.u.s.sdk.root.permissive_selinux

import p.l.e.x.u.s.sdk.usecases.root.permissive_selinux.ICanCheckPermissiveSeLinux

class SeLinux: ICanCheckPermissiveSeLinux {
    override fun checkPermissiveSeLinux(): Boolean =
        isPermissiveSeLinux() ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun isPermissiveSeLinux(): Boolean?
    }
}