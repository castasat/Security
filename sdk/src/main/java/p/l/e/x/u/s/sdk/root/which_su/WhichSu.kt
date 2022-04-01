package p.l.e.x.u.s.sdk.root.which_su

import p.l.e.x.u.s.sdk.SdkApplication.Companion.log
import p.l.e.x.u.s.sdk.usecases.root.which_su.ICanExecuteWhich
import p.l.e.x.u.s.sdk.usecases.root.which_su.ICanWhichSu

@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNUSED_VARIABLE")
class WhichSu : ICanWhichSu {

    /**
     * Try to use WHICH to find SU
     */
    override fun whichSu(): Boolean =
        lookForSuInPath(ExecuteWhichByJvm()) || lookForSuInPath(ExecuteWhichNatively())

    private fun lookForSuInPath(iCanExecuteWhich: ICanExecuteWhich): Boolean {
        // check PATH variable
        val pathVariable = System.getenv(PATH)
        //log("\$PATH = $pathVariable")

        // check if WHICH is present in system
        var parameter = WHICH
        var path = iCanExecuteWhich.executeWhich(parameter = parameter)
        //log("\"$WHICH\" command has found \"$parameter\" binary in PATH: $path")

        // use WHICH to check if SU is present in system
        parameter = SU
        path = iCanExecuteWhich.executeWhich(parameter = parameter)
        return if (path.isNotBlank()) {
            log("\"$WHICH\" command has found \"$parameter\" binary in PATH: $path")
            true
        } else {
            log("\"$WHICH\" command could not find \"$parameter\" binary in PATH")
            false
        }
    }

    companion object {
        private const val WHICH = "which"
        private const val SU = "su"
        private const val PATH = "PATH"
    }
}