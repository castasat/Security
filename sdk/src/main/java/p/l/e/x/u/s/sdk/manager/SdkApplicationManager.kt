package p.l.e.x.u.s.sdk.manager

import p.l.e.x.u.s.sdk.SdkApplication
import p.l.e.x.u.s.sdk.SdkApplication.Companion.log
import p.l.e.x.u.s.sdk.root.accessed.resetprop.Resetprop
import p.l.e.x.u.s.sdk.root.accessed.superuser.Superuser
import p.l.e.x.u.s.sdk.root.binary.busibox.FindBusyboxBinary
import p.l.e.x.u.s.sdk.root.binary.su.FindSuBinary
import p.l.e.x.u.s.sdk.root.build_tags.CheckOSBuildKeys
import p.l.e.x.u.s.sdk.root.hooks.FindHooks
import p.l.e.x.u.s.sdk.screenshots.SdkCallbacks
import p.l.e.x.u.s.sdk.usecases.ICanManageSdkApplication
import p.l.e.x.u.s.sdk.root.accessed.xposed.Xposed
import p.l.e.x.u.s.sdk.root.os_debuggable.CheckOsDebuggable
import p.l.e.x.u.s.sdk.root.os_insecure.OsInsecure
import p.l.e.x.u.s.sdk.root.permissive_selinux.SeLinux
import p.l.e.x.u.s.sdk.root.root_adb.RootAdbService
import p.l.e.x.u.s.sdk.root.sys_initd.SysInitd
import p.l.e.x.u.s.sdk.root.which_su.WhichSu
import p.l.e.x.u.s.sdk.root.write_permissions.WriteOnSystem

class SdkApplicationManager : ICanManageSdkApplication {

    override fun initialize(application: SdkApplication) {
        application.registerActivityLifecycleCallbacks(SdkCallbacks)
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
        log("hooksAreFound = $hooksAreFound")
    }

    override fun clear(application: SdkApplication) {
        application.unregisterActivityLifecycleCallbacks(SdkCallbacks)
    }
}