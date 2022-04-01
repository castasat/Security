package p.l.e.x.u.s.sdk.usecases.root.root_adb

interface ICanCheckRootAdbService {
    /**
     * check if ADB service with root access is present in system
     */
    fun checkRootAdbService(): Boolean
}