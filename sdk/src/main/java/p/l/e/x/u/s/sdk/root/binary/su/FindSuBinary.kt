package p.l.e.x.u.s.sdk.root.binary.su

import p.l.e.x.u.s.sdk.root.binary.FindBinary
import p.l.e.x.u.s.sdk.usecases.root.binary.su.ICanCheckIfSuBinaryFound

class FindSuBinary : ICanCheckIfSuBinaryFound {
    override fun checkIfSuBinaryFound(): Boolean =
        FindBinary().checkIfBinaryIsFound(SU_BINARY_NAME)

    companion object {
        private const val SU_BINARY_NAME = "su"
    }
}
