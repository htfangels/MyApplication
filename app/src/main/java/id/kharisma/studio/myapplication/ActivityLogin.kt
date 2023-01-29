package id.kharisma.studio.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import id.kharisma.studio.myapplication.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnDaftar.setOnClickListener{
            val intent = Intent(this, ActivityRegister::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener{
            val email = binding.EmailLogin.text.toString()
            val password = binding.Passwordlogin.text.toString()

            //Validasi Email
            if (email.isEmpty()){
                binding.EmailLogin.error = "Email Harus Diisi"
                binding.EmailLogin.requestFocus()
                return@setOnClickListener
            }
            //Validasi Email Tidak Sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.EmailLogin.error = "Email Tidak Valid"
                binding.EmailLogin.requestFocus()
                return@setOnClickListener
            }

            //Validasi Password
            if (password.isEmpty()){
                binding.Passwordlogin.error = "Password Harus Diisi"
                binding.Passwordlogin.requestFocus()
                return@setOnClickListener
            }

            //Validasi panjang password
            if (password.length < 6) {
                binding.Passwordlogin.error = "Password Minimal 6 Karakter"
                binding.Passwordlogin.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password)
        }

    }

    private fun LoginFirebase(email: String, password: String) {
       auth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener(this) {
               if (it.isSuccessful) {
                   Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                   val intent = Intent(this, MainActivity::class.java)
                   startActivity(intent)
               } else {
                   Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
               }

           }
    }


}