package p.l.e.x.u.s.sdk.root.which_su

import p.l.e.x.u.s.sdk.usecases.root.which_su.ICanExecuteWhich

class ExecuteWhichNatively : ICanExecuteWhich {
    override fun executeWhich(parameter: String): String =
        which(parameter = parameter) ?: EMPTY_STRING

    companion object {
        init {
            System.loadLibrary("sdk")
        }
        private const val EMPTY_STRING = ""
        private external fun which(parameter: String): String?
    }
}