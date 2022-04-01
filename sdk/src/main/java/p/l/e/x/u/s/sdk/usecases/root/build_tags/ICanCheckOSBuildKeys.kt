package p.l.e.x.u.s.sdk.usecases.root.build_tags

interface ICanCheckOSBuildKeys {
    /**
     * Check if android OS was built with TEST keys (custom built == rooted device)
     **/
    fun builtWithTestKeys(): Boolean

    /**
     * Check if android OS was built with DEV keys (custom built == rooted device)
     */
    fun builtWithDevKeys(): Boolean

    /**
     * Check if android OS was built without RELEASE keys (release keys - official built)
     * No release keys - we should warn the user
     */
    fun builtWithoutReleaseKeys(): Boolean
}
