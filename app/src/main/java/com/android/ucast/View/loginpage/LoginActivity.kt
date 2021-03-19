package com.android.ucast.View.loginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.ucast.Di.ViewModel.ViewModelProviderFactory
import com.android.ucast.Model.ResponseLogin
import com.android.ucast.Model.User
import com.android.ucast.R
import com.android.ucast.Session.SessionManager
import com.android.ucast.View.HomeActivity
import com.android.ucast.View.MainActivity
import com.android.ucast.ViewModel.ViewModelUCase
import com.android.ucast.databinding.ActivityLoginBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private lateinit var viewModel: ViewModelUCase
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val viewBinding = binding.root
        setContentView(viewBinding)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModelUCase::class.java)
        observe()

        binding.btnMasuk.setOnClickListener {
//            if (formUsername.text.toString().isEmpty()) {
//                formUsername.requestFocus()
//                formUsername.error = "Tidak Boleh Kosong"
//            }else if(katasandi.text.toString().isEmpty()) {
//                katasandi.requestFocus()
//                katasandi.error = "Tidak Boleh Kosong"
//            } else {
//                viewModel.dataLogin(formUsername.text.toString(), katasandi.text.toString())
//                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
//
//            }
            setValidation()
        }
    }

    private fun setValidation() {
        if (Email.text.toString().isEmpty()) {
            Email.requestFocus()
            Email.error = "Tidak Boleh Kosong"
        }else if (!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()) {
            Email.requestFocus()
            Email.error = "Email tidak Valid"
        }else if (Password.text.toString().isEmpty()) {
            Password.requestFocus()
            Password.error = "Tidak Boleh Kosong"
//        }else if (Password.text.length < 8) {
//            Password.requestFocus()
//            Password.error = "Password Tidak Boleh dari 8 Karakter"
        } else {
            viewModel.dataLogin(Email.text.toString(), Password.text.toString())

        }
    }

    private fun observe() {
        viewModel.isSuccess().observe(this, Observer { responseSuccess(it) })
        viewModel.isError().observe(this, Observer { responseError(it) })
    }

    private fun responseError(it: Throwable?) {
        Toast.makeText(applicationContext, "Akun tidak terdaftar", Toast.LENGTH_SHORT).show()
    }

    private fun responseSuccess(it: ResponseLogin?) {
        if (it?.status == true) {
            //Isi Shared Preference
             val session = SessionManager(this)
            val data = it.user
            session.email = data?.email?.get(0).toString()
            session.id = data?.id
            session.name = data?.name?.get(0).toString()
            session.image = data?.imgProfile
            session.login = true
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}