package p.l.e.x.u.s.security.app.base

sealed class UIBaseEvents

enum class ValidationError {
    NAME_REQUIRED,
    PHONE_REQUIRED,
    ADDRESS_REQUIRED,
    PHONE_INVALID,
    EMAIL_INVALID
}

data class UIBaseEventsInvalidInput(val error: ValidationError) : UIBaseEvents()

object UIBaseEventsProgressShow : UIBaseEvents()
object UIBaseEventsProgressHide : UIBaseEvents()
object UIBaseEventsGoBack : UIBaseEvents()
object UIBaseEventsGoBackForce : UIBaseEvents()
object UIBaseEventsHideKeyboard : UIBaseEvents()
