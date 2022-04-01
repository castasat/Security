package p.l.e.x.u.s.sdk.root.accessed.xposed

import p.l.e.x.u.s.sdk.root.accessed.FileAccessed
import p.l.e.x.u.s.sdk.usecases.root.accessed.xposed.ICanCheckXposed

class Xposed : ICanCheckXposed {
    override fun checkXposed(): Boolean {
        var result = false
        val fileAccessed = FileAccessed()
        for (xposedPathToFile in XPOSED_PATHS_ARRAY) {
            result = fileAccessed.checkFileIsAccessed(xposedPathToFile)
            if (result) break
        }
        return result
    }

    companion object {
        private val XPOSED_PATHS_ARRAY = listOf(
            "/system/lib/libxposed_art.so",
            "/system/lib64/libxposed_art.so",
            "/system/xposed.prop",
            "/cache/recovery/xposed.zip",
            "/system/framework/XposedBridge.jar",
            "/system/bin/app_process64_xposed",
            "/system/bin/app_process32_xposed",
            "/magisk/xposed/system/lib/libsigchain.so",
            "/magisk/xposed/system/lib/libart.so",
            "/magisk/xposed/system/lib/libart-disassembler.so",
            "/magisk/xposed/system/lib/libart-compiler.so",
            "/system/bin/app_process32_orig",
            "/system/bin/app_process64_orig"
        )
    }
}