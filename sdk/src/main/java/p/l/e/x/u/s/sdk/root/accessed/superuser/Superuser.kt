package p.l.e.x.u.s.sdk.root.accessed.superuser

import p.l.e.x.u.s.sdk.usecases.root.accessed.superuser.ICanCheckSuperuser
import p.l.e.x.u.s.sdk.root.accessed.FileAccessed

class Superuser : ICanCheckSuperuser {
    override fun checkSuperuser(): Boolean =
        FileAccessed().checkFileIsAccessed(SUPERUSER_PATH_TO_FILE)

    companion object {
        private const val SUPERUSER_PATH_TO_FILE = "/system/app/Superuser.apk"
    }
}