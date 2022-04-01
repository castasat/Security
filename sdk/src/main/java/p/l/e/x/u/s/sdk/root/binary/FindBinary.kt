package p.l.e.x.u.s.sdk.root.binary

import p.l.e.x.u.s.sdk.usecases.root.binary.ICanCheckIfBinaryIsFound

class FindBinary: ICanCheckIfBinaryIsFound {
    override fun checkIfBinaryIsFound(binaryName: String): Boolean =
        isBinaryFound(binaryName) ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun isBinaryFound(binaryName: String): Boolean?
    }
}