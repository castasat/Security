package p.l.e.x.u.s.security.app.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import p.l.e.x.u.s.security.app.navigation.Screens
import p.l.e.x.u.s.security.app.base.BaseViewModel
import p.l.e.x.u.s.security.app.base.navigation.Navigator
import p.l.e.x.u.s.sdk.root.which_su.WhichSu
import p.l.e.x.u.s.security.BuildConfig
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
@FlowPreview
class SecurityViewModel @Inject constructor(
    private val navigator: Navigator,
    private val screen: Screens,
    private val whichSu: WhichSu
) : BaseViewModel() {

    private val _whichSuResultText = MutableLiveData<String>()
    val whichSuResultTextLiveData: LiveData<String>
        get() = _whichSuResultText

    fun whichSu() {
        when (whichSu.whichSu()) {
            true -> _whichSuResultText.postValue(
                "\"which\" command has found \"su\" binary in \$PATH"
            )
            else -> _whichSuResultText.postValue(
                "\"which\" command could not find \"su\" binary in \$PATH"
            )
        }
        navigator.open(screen.showWhichSuScreen())
    }

    companion object {
        const val VERSION_NAME = BuildConfig.VERSION_NAME
        const val VERSION_CODE = BuildConfig.VERSION_CODE
        const val BUILD_VARIANT = BuildConfig.BUILD_TYPE
        const val BUILD_DATE = BuildConfig.BUILD_DATE
    }
}