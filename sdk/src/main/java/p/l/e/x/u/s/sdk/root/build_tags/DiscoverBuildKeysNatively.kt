package p.l.e.x.u.s.sdk.root.build_tags

import p.l.e.x.u.s.sdk.usecases.root.build_tags.ICanDiscoverBuildKeys

class DiscoverBuildKeysNatively : ICanDiscoverBuildKeys {
    override fun discoverBuildKeys(buildKeys: String): Boolean =
        inOsBuildKeys(buildKeys) ?: false

    companion object {
        init {
            System.loadLibrary("sdk")
        }

        private external fun inOsBuildKeys(buildKeys: String): Boolean?
    }
}