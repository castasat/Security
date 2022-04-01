package p.l.e.x.u.s.security.app.ui.fragment.example

import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import p.l.e.x.u.s.security.R
import p.l.e.x.u.s.security.app.ui.activity.SecurityViewModel
import p.l.e.x.u.s.security.app.base.BaseDataBindingFragment
import p.l.e.x.u.s.security.databinding.FragmentExampleBinding

@ExperimentalCoroutinesApi
@AndroidEntryPoint
@FlowPreview
class ExampleFragment:
    BaseDataBindingFragment<FragmentExampleBinding, SecurityViewModel>(
        R.layout.fragment_example
    ) {
    override val viewModel: SecurityViewModel by activityViewModels()
}