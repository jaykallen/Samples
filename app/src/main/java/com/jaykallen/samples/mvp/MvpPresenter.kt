package com.jaykallen.samples.mvp

// 2018-05-22: Note that the model Person is held by the presenter.
// The presenter then can receive a load message command and then execute show message in the view
// which updates the TextView

class MvpPresenter(private val view: MvpContract.View): MvpContract.Presenter {
    private val person = Person()

    override fun loadMessage() {
        // This updates textviews and toasts directly from the presenter and is activated by the view.
        val message = "Hi " + person.firstName + " " + person.lastName
        view.showMessage(message)
        view.showError(message)
    }

    override fun updateModel(firstName: String, lastName: String) {
        // This updates the model directly from the view.
        person.firstName = firstName
        person.lastName = lastName
    }


}
