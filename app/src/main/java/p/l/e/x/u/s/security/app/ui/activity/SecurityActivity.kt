package p.l.e.x.u.s.security.app.ui.activity

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import p.l.e.x.u.s.R
import p.l.e.x.u.s.security.app.ui.base.BaseActivity

@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SecurityActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)
    }
}