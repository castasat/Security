package p.l.e.x.u.s.sdk.root.build_tags

import p.l.e.x.u.s.sdk.usecases.root.build_tags.ICanCheckOSBuildKeys

class CheckOSBuildKeys : ICanCheckOSBuildKeys {
    override fun builtWithTestKeys(): Boolean =
        DiscoverBuildKeysByJvm().discoverBuildKeys(TEST_OS_BUILD_KEYS) ||
                DiscoverBuildKeysNatively().discoverBuildKeys(TEST_OS_BUILD_KEYS)

    override fun builtWithDevKeys(): Boolean =
        DiscoverBuildKeysByJvm().discoverBuildKeys(DEV_OS_BUILD_KEYS) ||
                DiscoverBuildKeysNatively().discoverBuildKeys(DEV_OS_BUILD_KEYS)

    // TODO Если нет релизных ключей - это подозрительно, надо показывать предупреждение
    override fun builtWithoutReleaseKeys(): Boolean =
        !(DiscoverBuildKeysByJvm().discoverBuildKeys(RELEASE_OS_BUILD_KEYS) ||
                DiscoverBuildKeysNatively().discoverBuildKeys(RELEASE_OS_BUILD_KEYS))

    companion object {
        private const val TEST_OS_BUILD_KEYS = "test-keys"
        private const val DEV_OS_BUILD_KEYS = "dev-keys"
        private const val RELEASE_OS_BUILD_KEYS = "release-keys"
    }
}