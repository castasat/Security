package p.l.e.x.u.s.sdk.usecases.root.which_su

interface ICanExecuteWhich {
    /**
     * Returns the result of "which parameter" linux command execution
     * i.e.   > which which
     * result > /system/bin/which
     */
    fun executeWhich(parameter: String): String
}