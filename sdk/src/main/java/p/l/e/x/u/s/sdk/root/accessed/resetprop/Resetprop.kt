package p.l.e.x.u.s.sdk.root.accessed.resetprop

import p.l.e.x.u.s.sdk.usecases.root.accessed.resetprop.ICanCheckResetprop
import p.l.e.x.u.s.sdk.root.accessed.FileAccessed

class Resetprop : ICanCheckResetprop {
    override fun checkResetprop(): Boolean =
        FileAccessed().checkFileIsAccessed(RESETPROP_PATH_TO_FILE)

    companion object {
        private const val RESETPROP_PATH_TO_FILE = "/data/magisk/resetprop"
    }
}