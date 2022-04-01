package p.l.e.x.u.s.sdk.root.binary.busibox

import p.l.e.x.u.s.sdk.root.binary.FindBinary
import p.l.e.x.u.s.sdk.usecases.root.binary.busibox.ICanCheckIfBusyboxBinaryFound

class FindBusyboxBinary : ICanCheckIfBusyboxBinaryFound {
    override fun checkIfBusyboxBinaryFound(): Boolean =
        FindBinary().checkIfBinaryIsFound(BUSYBOX_BINARY_NAME)

    companion object {
        private const val BUSYBOX_BINARY_NAME = "busybox"
    }
}