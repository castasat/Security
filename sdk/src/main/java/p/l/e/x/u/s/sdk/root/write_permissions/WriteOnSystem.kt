package p.l.e.x.u.s.sdk.root.write_permissions

import p.l.e.x.u.s.sdk.usecases.root.write_permissions.ICanCheckWriteAccessOnSystem

class WriteOnSystem : ICanCheckWriteAccessOnSystem {
    override fun checkWriteAccessOnSystem(): Boolean =
        checkWriteAccess() ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun checkWriteAccess(): Boolean?
    }
}