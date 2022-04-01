package p.l.e.x.u.s.sdk.root.accessed

import p.l.e.x.u.s.sdk.usecases.root.accessed.ICanCheckFileIsAccessed

class FileAccessed : ICanCheckFileIsAccessed {
    override fun checkFileIsAccessed(pathToFile: String): Boolean =
        isAccessedFile(pathToFile) ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun isAccessedFile(pathToFile: String): Boolean?
    }
}