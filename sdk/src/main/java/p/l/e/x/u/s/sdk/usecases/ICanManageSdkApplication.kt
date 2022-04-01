package p.l.e.x.u.s.sdk.usecases

import p.l.e.x.u.s.sdk.SdkApplication

interface ICanManageSdkApplication {
    /**
     * do the initial preparations for all the enabled security features
     */
    fun initialize(application: SdkApplication)

    /**
     * do final cleanup for all the security features
     */
    fun clear(application: SdkApplication)
}