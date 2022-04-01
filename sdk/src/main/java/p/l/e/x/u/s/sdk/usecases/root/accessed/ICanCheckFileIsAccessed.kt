package p.l.e.x.u.s.sdk.usecases.root.accessed

interface ICanCheckFileIsAccessed {
    /**
     * Checks if given file is accessed
     */
    fun checkFileIsAccessed(pathToFile: String): Boolean
}