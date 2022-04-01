package p.l.e.x.u.s.sdk.manager

import p.l.e.x.u.s.sdk.SdkApplication
import p.l.e.x.u.s.sdk.usecases.ICanManageSdkApplication

class SdkApplicationManager : ICanManageSdkApplication {

    override fun initialize(application: SdkApplication) {
        /*TODO application.registerActivityLifecycleCallbacks(SdkCallbacks)
        // TODO show on UI and disable corporate application usage
        val suIsPresentInSystem = WhichSu().whichSu()
        log("suIsPresentInSystem = $suIsPresentInSystem")
        val osBuiltWithTestKeys = CheckOSBuildKeys().builtWithTestKeys()
        log("osBuiltWithTestKeys = $osBuiltWithTestKeys")
        val osBuiltWithDevKeys = CheckOSBuildKeys().builtWithDevKeys()
        log("osBuiltWithDevKeys = $osBuiltWithDevKeys")
        // TODO show warning if there are no release keys
        val osBuiltWithoutReleaseKeys = CheckOSBuildKeys().builtWithoutReleaseKeys()
        log("osBuiltWithoutReleaseKeys = $osBuiltWithoutReleaseKeys")
        val systemPathsHaveWriteAccess = WriteOnSystem().checkWriteAccessOnSystem()
        log("systemPathsHaveWriteAccess = $systemPathsHaveWriteAccess")
        val osDebuggable = CheckOsDebuggable().checkIfSystemIsDebuggable()
        log("osDebuggable = $osDebuggable")
        val isPermissiveSeLinux = SeLinux().checkPermissiveSeLinux()
        log("isPermissiveSeLinux = $isPermissiveSeLinux")
        val adbServiceWithRoot = RootAdbService().checkRootAdbService()
        log("adbServiceWithRoot = $adbServiceWithRoot")
        val osInsecure = OsInsecure().checkIfOsInsecure()
        log("osInsecure = $osInsecure")
        val sysInitd = SysInitd().checkSysInitd()
        log("sysInitd = $sysInitd")
        val isSuperuserInstalled = Superuser().checkSuperuser()
        log("isSuperuserInstalled = $isSuperuserInstalled")
        val isResetpropInstalled = Resetprop().checkResetprop()
        log("isResetpropInstalled = $isResetpropInstalled")
        val isXposedInstalled = Xposed().checkXposed()
        log("isXposedInstalled = $isXposedInstalled")
        val isSuBinaryFound = FindSuBinary().checkIfSuBinaryFound()
        log("isSuBinaryFound = $isSuBinaryFound")
        val isBusyboxBinaryFound = FindBusyboxBinary().checkIfBusyboxBinaryFound()
        log("isBusyboxBinaryFound = $isBusyboxBinaryFound")
        val hooksAreFound = FindHooks().checkIfHooksFound()
        log("hooksAreFound = $hooksAreFound")*/
    }

    override fun clear(application: SdkApplication) {
        /*TODO application.unregisterActivityLifecycleCallbacks(SdkCallbacks)*/
    }
}