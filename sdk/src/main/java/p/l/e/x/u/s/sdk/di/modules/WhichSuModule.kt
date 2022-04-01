package p.l.e.x.u.s.sdk.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import p.l.e.x.u.s.sdk.root.which_su.WhichSu
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WhichSuModule {
    @Singleton
    @Provides
    fun provideWhichSu(): WhichSu {
        return WhichSu()
    }
}