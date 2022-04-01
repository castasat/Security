package p.l.e.x.u.s.sdk.root.root_adb

import p.l.e.x.u.s.sdk.usecases.root.root_adb.ICanCheckRootAdbService

class RootAdbService : ICanCheckRootAdbService {
    override fun checkRootAdbService(): Boolean =
        hasRootAdb() ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun hasRootAdb(): Boolean?
    }
}