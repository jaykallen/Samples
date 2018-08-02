package com.jaykallen.samples.firebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.jaykallen.samples.R
import kotlinx.android.synthetic.main.activity_fire_sign.*

// 4/30/18: Converted to Kotlin

class FireSignActivity : AppCompatActivity() {
    private var mFirebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_sign)

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance()

        signupButton.setOnClickListener {
            var password = passwordField.text.toString()
            var email = emailField.text.toString()

            password = password.trim { it <= ' ' }
            email = email.trim { it <= ' ' }

            if (password.isEmpty() || email.isEmpty()) {
                val builder = AlertDialog.Builder(this@FireSignActivity)
                builder.setMessage("Sign up!")
                        .setTitle("Please Sign Up")
                        .setPositiveButton(android.R.string.ok, null)
                val dialog = builder.create()
                dialog.show()
            } else {
                mFirebaseAuth!!.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@FireSignActivity, object : OnCompleteListener<AuthResult> {
                            override fun onComplete(task: Task<AuthResult>) {
                                if (task.isSuccessful()) {
                                    val intent = Intent(this@FireSignActivity, FirebaseActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    startActivity(intent)
                                } else {
                                    val builder = AlertDialog.Builder(this@FireSignActivity)
                                    builder.setMessage(task.getException()?.message)
                                            .setTitle("Login")
                                            .setPositiveButton(android.R.string.ok, null)
                                    val dialog = builder.create()
                                    dialog.show()
                                }
                            }
                        })
            }
        }
    }
}
