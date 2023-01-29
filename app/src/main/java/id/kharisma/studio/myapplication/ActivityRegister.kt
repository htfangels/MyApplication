package id.kharisma.studio.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import id.kharisma.studio.myapplication.databinding.ActivityRegisterBinding

class ActivityRegister : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener{
            val intent = Intent (this,ActivityLogin::class.java)
            startActivity(intent)
        }


        binding.btnDaftar.setOnClickListener{
            val name = binding.NamaRegister.text.toString()
            val email = binding.Email.text.toString()
            val password = binding.Password.text.toString()

            //Validasi Nama
            if (name.isEmpty()){
                binding.NamaRegister.error = "Nama Lengkap Harus Diisi"
                binding.NamaRegister.requestFocus()
                return@setOnClickListener
            }
            //Validasi Email
            if (email.isEmpty()){
                binding.Email.error = "Email Harus Diisi"
                binding.Email.requestFocus()
                return@setOnClickListener
            }
            //Validasi Email Tidak Sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.Email.error = "Email Tidak Valid"
                binding.Email.requestFocus()
                return@setOnClickListener
            }

            //Validasi Password
            if (password.isEmpty()){
                binding.Password.error = "Password Harus Diisi"
                binding.Password.requestFocus()
                return@setOnClickListener
            }

            //Validasi panjang password
            if (password.length < 6) {
                binding.Password.error = "Password Minimal 6 Karakter"
                binding.Password.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(name,email,password)
        }
    }

    private fun RegisterFirebase(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, ActivityLogin::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }

            }
    }


}

