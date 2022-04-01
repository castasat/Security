package p.l.e.x.u.s.security.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class EditBaseViewModel : BaseViewModel() {
    open val needGoBackAfterSave = true
    var needToShowProgress = true

    private val _hasChangesEvent = MutableLiveData(false)
    val hasChangesEvent: LiveData<Boolean>
        get() = _hasChangesEvent

    fun onSaveClick() {
        viewModelScope.launch {
            sendUiEvent(UIBaseEventsHideKeyboard)
            if (needToShowProgress) {
                withProgress {
                    trySave()
                }
            } else {
                trySave()
            }
        }
    }

    private suspend fun trySave() {
        try {
            if (save() && needGoBackAfterSave) {
                sendUiEvent(UIBaseEventsGoBackForce)
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun onDeleteClick() {
        viewModelScope.launch {
            sendUiEvent(UIBaseEventsHideKeyboard)
            if (needToShowProgress) {
                withProgress {
                    tryDelete()
                }
            } else {
                tryDelete()
            }
        }
    }

    private suspend fun tryDelete() {
        try {
            delete()
        } catch (exception: Exception) {
            exception.printStackTrace()
        } finally {
            sendUiEvent(UIBaseEventsGoBackForce)
        }
    }

    protected abstract suspend fun save(): Boolean

    protected open suspend fun delete() {}

    protected fun invalidateChanges() {
        _hasChangesEvent.postValue(hasChanges())
    }

    protected abstract fun hasChanges(): Boolean
}