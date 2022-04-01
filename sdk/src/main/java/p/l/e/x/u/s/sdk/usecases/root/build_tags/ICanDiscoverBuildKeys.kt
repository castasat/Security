package p.l.e.x.u.s.sdk.usecases.root.build_tags

interface ICanDiscoverBuildKeys {
    /**
     * Discover if provided build tags string is present in the android system
     */
    fun discoverBuildKeys(buildKeys: String): Boolean
}