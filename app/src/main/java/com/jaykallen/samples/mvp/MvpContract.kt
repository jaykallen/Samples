package com.jaykallen.samples.mvp

interface MvpContract {

    interface View {
        fun showMessage(message: String)
        fun showError(error: String)
    }

    interface Presenter {
        fun loadMessage()
        fun updateModel(firstName: String, lastName: String)
    }

}
