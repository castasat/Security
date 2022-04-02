package p.l.e.x.u.s.security.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import p.l.e.x.u.s.security.BR

abstract class BaseDataBindingFragment<B : ViewDataBinding, VM : BaseViewModel>(
    private val layoutId: Int
) : BaseFragment() {

    private var binding: B? = null

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.setVariable(BR.viewModel, viewModel)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun tryGoBack() {
        val savedVM = viewModel as? EditBaseViewModel

        if (savedVM != null) {
            if (savedVM.hasChangesEvent.value == true && viewModel.needToShowLoseDataAlert) {
                showLoseDataAlert()
            } else {
                super.tryGoBack()
            }
        } else {
            super.tryGoBack()
        }
    }

    protected open fun showInvalidInput(error: ValidationError) {}
}
