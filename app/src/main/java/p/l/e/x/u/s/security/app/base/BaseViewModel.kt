package p.l.e.x.u.s.security.app.base

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Suppress("unused")
abstract class BaseViewModel : ViewModel() {
    open var needToShowLoseDataAlert = true

    private val _uiBaseEvents = SingleLiveEvent<UIBaseEvents>()
    val uiBaseEvents: LiveData<UIBaseEvents>
        get() = _uiBaseEvents

    protected val _progressEvent = MutableLiveData(false)
    val progressEvent: LiveData<Boolean>
        get() = _progressEvent

    private val workerJob = SupervisorJob()
    protected val viewModelWorkerScope = CoroutineScope(Dispatchers.IO + workerJob)

    override fun onCleared() {
        super.onCleared()
        workerJob.cancel()
    }

    @MainThread
    protected suspend fun withProgress(action: suspend () -> Unit) {
        if (_progressEvent.value == true) return

        _progressEvent.value = true
        action.invoke()
        _progressEvent.value = false
    }

    protected fun sendUiEvent(uiEvent: UIBaseEvents) {
        _uiBaseEvents.postValue(uiEvent)
    }
}