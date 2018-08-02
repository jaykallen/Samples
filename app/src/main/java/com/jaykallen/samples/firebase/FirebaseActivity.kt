package com.jaykallen.samples.firebase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.jaykallen.samples.R
import kotlinx.android.synthetic.main.activity_firebase.*

// 6/6/17: Attempt at using firebase within an Activity.  I registered the application at firebase.google.com
// I added the google-services.json file to the app directory.  I add references in project and app gradle.
// Used these instructions: https://www.sitepoint.com/creating-a-cloud-backend-for-your-android-app-using-firebase/
// This actually works! You can see logins and data added in console.firebase.google.com
// Next step is to test this out between multiple devices?  Does it update in real time?
// 4/30/18: Converted to Kotlin

class FirebaseActivity : AppCompatActivity() {
    private val tag = "TagFirebaseActivity"
    private var mFirebaseAuth: FirebaseAuth? = null
    private var mFirebaseUser: FirebaseUser? = null
    private var mDatabase: DatabaseReference? = null
    private var mUserId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mFirebaseAuth?.getCurrentUser()

        if (mFirebaseUser == null) {
            loadLogInView()
        } else {
            mUserId = mFirebaseUser?.getUid()
            mDatabase = FirebaseDatabase.getInstance().getReference()
            setupList()
        }
    }

    private fun loadLogInView() {
        val intent = Intent(this, FireLoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun onAddClick(view: View) {
        // Add items via the Button and EditText at the bottom of the view.
        mDatabase?.child("users")?.child(mUserId)?.child("items")?.push()?.child("title")?.setValue(todoText.text.toString())
        todoText.setText("")
    }

    private fun setupList() {
        // Set up ListView
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1)
        listView.adapter = adapter

        // Use Firebase to populate the list.
        mDatabase!!.child("users").child(mUserId).child("items").addChildEventListener(
                object : ChildEventListener {
                    override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {
                        adapter.add(dataSnapshot.child("title").getValue() as String)
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot, s: String) {}
                    override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                        adapter.remove(dataSnapshot.child("title").getValue() as String)
                    }

                    override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {}
                    override fun onCancelled(databaseError: DatabaseError) {}
                })

    }

}
