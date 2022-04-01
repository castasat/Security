package p.l.e.x.u.s.sdk.root.hooks

import p.l.e.x.u.s.sdk.usecases.root.hooks.ICanCheckIfHooksFound

class FindHooks : ICanCheckIfHooksFound {
    override fun checkIfHooksFound(): Boolean =
        findHooks() ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun findHooks(): Boolean?
    }
}
