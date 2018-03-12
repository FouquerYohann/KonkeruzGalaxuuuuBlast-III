package com.example.yfouquer.konkeruzgalaxuuuublast_iii

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Data.UserData
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.DataBaseTools.mAuth
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.R.layout.activity_login
import com.example.yfouquer.konkeruzgalaxuuuublast_iii.Tools.DataBaseTools
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {

    private var mProgressBar: ProgressDialog? = null

    private lateinit var mEmail: String
    private lateinit var mPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
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
                   UserData.uid = FirebaseAuth.getInstance().currentUser!!.uid
                    DataBaseTools.buildingData(applicationContext)
                    DataBaseTools.defenseData(applicationContext)
                    DataBaseTools.shipData(applicationContext)
                    DataBaseTools.techData(applicationContext)
                    DataBaseTools.userData(UserData.uid)
                    Handler().postDelayed({
                        val intent = Intent(this, PlanetActivity::class.java)
                        intent.putExtra("planet",0)
                        startActivity(intent)
                        //startActivity(Intent(this,GameActivity::class.java))
                    }, 2000 )
                } else {
                    Toast.makeText(this, "Sign in Failed", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            mAuth!!.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener { task ->
                mProgressBar!!.hide()
                if (task.isSuccessful) {

                    if (mAuth == null) {
                        println("mAuth = null")
                    }else{
                        println("mAuth = ${mAuth}")
                    }

                    DataBaseTools.mAuth = mAuth
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