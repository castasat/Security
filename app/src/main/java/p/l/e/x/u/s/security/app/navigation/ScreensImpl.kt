package p.l.e.x.u.s.security.app.navigation

import p.l.e.x.u.s.security.app.base.navigation.Screen
import p.l.e.x.u.s.security.app.ui.fragment.example.ExampleFragmentDirections

class ScreensImpl : Screens {
    override fun showWhichSuScreen() = Screen {
        ExampleFragmentDirections.actionExampleFragmentToWhichSuFragment()
    }
}
