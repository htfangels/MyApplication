package id.kharisma.studio.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_input_data.*

class InputDataActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var inputNama : EditText
    private lateinit var inputNoTelp : EditText
    private lateinit var jenisampah : EditText
    private lateinit var inputBerat : EditText
    private lateinit var inputTgl : EditText
    private lateinit var inputAlm : EditText
    private lateinit var btnCheckout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data)

        inputNama = findViewById(R.id.inputNama)
        inputNoTelp = findViewById(R.id.inputNoTelp)
        jenisampah = findViewById(R.id.jenisampah)
        inputBerat = findViewById(R.id.inputBerat)
        inputTgl = findViewById(R.id.inputTgl)
        inputAlm = findViewById(R.id.inputAlm)
        btnCheckout = findViewById(R.id.btnCheckout)

        btnCheckout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val nama: String = inputNama.text.toString().trim()
        val notelp: String = inputNoTelp.text.toString().trim()
        val jsampah: String = jenisampah.text.toString().trim()
        val berat: String = inputBerat.text.toString().trim()
        val tanggal: String = inputTgl.text.toString().trim()
        val bulan: String = inputBulan.text.toString().trim()
        val tahun: String = inputTahun.text.toString().trim()
        val alamat: String = inputAlm.text.toString().trim()


        if (nama.isEmpty()) {
            inputNama.error = "Silahkan Isi Nama Anda"
            return
        }
        if (notelp.isEmpty()) {
            inputNoTelp.error = "Silahkan Isi Alamat Penjemputan Sampah Anda"
            return
        }
        if (jsampah.isEmpty()) {
            jenisampah.error = "Silahkan Isi Jenis Sampah Anda"
            return
        }
        if (berat.isEmpty()) {
            inputBerat.error = "Silahkan Isi Berapa Berat Sampah Anda"
            return
        }
        if (tanggal.isEmpty()) {
            inputTgl.error = "Silahkan Isi Tanggal Penjemputan Sampah Anda"
            return
        }
        if (bulan.isEmpty()) {
            inputBulan.error = "Silahkan Isi Bulan Penjemputan Sampah Anda"
            return
        }
        if (tahun.isEmpty()) {
            inputTahun.error = "Silahkan Isi Tahun Penjemputan Sampah Anda"
            return
        }
        if (alamat.isEmpty()) {
            inputAlm.error = "Silahkan Isi Alamat Penjemputan Sampah Anda"
            return
        }


        val ref = FirebaseDatabase.getInstance().getReference("TukarSampah")

        val tkrsmpahId = ref.push().key

        val tkrsmph = TukarSampah(tkrsmpahId,nama,notelp,jsampah,berat,tanggal,bulan, tahun, alamat)

        if (tkrsmpahId != null) {
            ref.child(tkrsmpahId).setValue(tkrsmph).addOnCompleteListener{
                Toast.makeText(applicationContext, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ActivityTukarBerhasil::class.java)
                startActivity(intent)
            }
        }
    }

}