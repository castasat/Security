package p.l.e.x.u.s.sdk.root.build_tags

import p.l.e.x.u.s.sdk.usecases.root.build_tags.ICanDiscoverBuildKeys

class DiscoverBuildKeysByJvm : ICanDiscoverBuildKeys {
    override fun discoverBuildKeys(buildKeys: String): Boolean =
        OS_BUILD_TAGS?.let { osBuildKeys ->
            when {
                osBuildKeys.contains(buildKeys) -> true
                else -> false
            }
        } ?: false

    companion object {
        private val OS_BUILD_TAGS: String? = android.os.Build.TAGS
    }
}