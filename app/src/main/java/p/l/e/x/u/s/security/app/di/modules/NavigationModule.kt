package p.l.e.x.u.s.security.app.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import p.l.e.x.u.s.security.app.navigation.Screens
import p.l.e.x.u.s.security.app.navigation.ScreensImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideScreens(): Screens {
        return ScreensImpl()
    }
}