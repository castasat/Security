package p.l.e.x.u.s.sdk.usecases.root.binary

interface ICanCheckIfBinaryIsFound {
    /**
     * Checks if given binary is found in android system
     */
    fun checkIfBinaryIsFound(binaryName: String): Boolean
}