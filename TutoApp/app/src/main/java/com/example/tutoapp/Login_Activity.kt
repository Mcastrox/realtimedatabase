package com.example.tutoapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth

class Login_Activity : AppCompatActivity() {
    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_)
        txtUser = findViewById(R.id.txtUser)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar = findViewById(R.id.progressBar2)
        auth = FirebaseAuth.getInstance()

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        );
    }

    fun forgotPassword(view: View) {
        startActivity(Intent(this, Forgotpass_Activity::class.java))
    }

    fun register(view: View) {
        startActivity(Intent(this, Register_Activity::class.java))
    }

    fun login(view: View) {
        loginUser()
    }

    private fun loginUser() {
        val user: String = txtUser.text.toString()
        val pass: String = txtPassword.text.toString()


        if (!verificarUser(user)) {
            txtUser.setError("direccion invalida")

        }


        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
            progressBar.visibility = View.VISIBLE

            auth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        action()
                        progressBar.visibility = View.GONE
                    } else {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "Error al autenticar", Toast.LENGTH_LONG).show()
                    }
                }
        }else {
            Toast.makeText(this, "Por favor rellene todos los campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun action() {
        val intent = Intent (this, CentralActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun verificarUser(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}
