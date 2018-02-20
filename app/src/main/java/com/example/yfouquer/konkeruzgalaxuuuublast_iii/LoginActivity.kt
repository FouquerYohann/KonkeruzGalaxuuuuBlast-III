package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R.layout.activity_login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mDataBaseReference: DatabaseReference? = null
    private var mDataBase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private var mProgressBar: ProgressDialog? = null

    private lateinit var mEmail: String
    private lateinit var mPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)

        mDataBase = FirebaseDatabase.getInstance()
        mDataBaseReference = mDataBase!!.getReference("konkeruzgalaxuuuublast-iii")
        mAuth = FirebaseAuth.getInstance()

        mProgressBar = ProgressDialog(this)

        register_button.setOnClickListener { login_register(false) }
        sign_in_button.setOnClickListener { login_register(true) }
    }

    private fun login_register(login: Boolean) {
        mEmail = email.text.toString()
        mPassword = password.text.toString()

        if (TextUtils.isEmpty(mEmail) && !isValidMail(mEmail)) {
            email.requestFocus()
            Toast.makeText(this, "Invalid mail", Toast.LENGTH_SHORT).show()
        }
        if (!isValidPassword(mPassword)) {
            password.requestFocus()
            Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
        }
        mProgressBar!!.setMessage("Doing stuff bitch wait !!")
        mProgressBar!!.show()

        if (login) {
            mAuth!!.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener { task ->
                mProgressBar!!.hide()
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sign in Successfully", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainActivity::class.java))
                } else {
                    Toast.makeText(this, "Sign in Failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            mAuth!!.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener { task ->
                mProgressBar!!.hide()
                if (task.isSuccessful) {
                    val uid = mAuth!!.uid
                    Toast.makeText(this, "Register Successfully", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    private fun isValidPassword(mPassword: String?): Boolean {
        return true
    }

    private fun isValidMail(mEmail: String?): Boolean {
        return true
    }
}