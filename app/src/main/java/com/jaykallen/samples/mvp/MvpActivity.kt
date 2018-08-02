package com.jaykallen.samples.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

import com.jaykallen.samples.R
import kotlinx.android.synthetic.main.activity_mvp.*

// Created by Jay Kallen on 1/25/18. Base upon this example:
// https://android.jlelse.eu/architecture-patterns-in-android-abf99f2b6f70
// 2018-05-22: Working Sample of MVP
// Every click goes directly to the presenter to have logic performed.
// Every method which updates a field or toast is called from the presenter.

class MvpActivity : AppCompatActivity(), MvpContract.View {
    private var presenter: MvpContract.Presenter = MvpPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
    }

    override fun showMessage(msg: String) {
        messageTextView.text = msg
    }

    override fun showError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }

    fun onUpdateClick(view: View) {
        presenter.updateModel(firstName.getText().toString(), lastName.getText().toString())
    }

    fun onShowClick(view: View) {
        presenter.loadMessage()
    }
}
