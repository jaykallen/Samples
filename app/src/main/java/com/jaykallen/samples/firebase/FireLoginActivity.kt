package com.jaykallen.samples.firebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.jaykallen.samples.R
import kotlinx.android.synthetic.main.activity_fire_login.*

class FireLoginActivity : AppCompatActivity() {
    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_login)

        signUpText.setOnClickListener {
            val intent = Intent(this@FireLoginActivity, FireSignActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            var email = emailField.text.toString()
            var password = passwordField.text.toString()

            email = email.trim { it <= ' ' }
            password = password.trim { it <= ' ' }

            if (email.isEmpty() || password.isEmpty()) {
                val builder = AlertDialog.Builder(this@FireLoginActivity)
                builder.setMessage("Login Error")
                        .setTitle("Login Error Title")
                        .setPositiveButton(android.R.string.ok, null)
                val dialog = builder.create()
                dialog.show()
            } else {
                mFirebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@FireLoginActivity, object : OnCompleteListener<AuthResult> {
                            override fun onComplete(task: Task<AuthResult>) {
                                if (task.isSuccessful()) {
                                    val intent = Intent(this@FireLoginActivity, FirebaseActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    startActivity(intent)
                                } else {
                                    val builder = AlertDialog.Builder(this@FireLoginActivity)
                                    builder.setMessage(task.getException()?.message)
                                            .setTitle("Login Error")
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
