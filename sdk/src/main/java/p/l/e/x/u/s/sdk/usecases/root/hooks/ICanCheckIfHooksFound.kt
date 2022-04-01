package p.l.e.x.u.s.sdk.usecases.root.hooks

interface ICanCheckIfHooksFound {
    /**
     * Check if hooks are found with running processes
     */
    fun checkIfHooksFound(): Boolean
}