package com.uzunu.project1uzunu

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DuaActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener {
    // : =>extends pewarisan / inhherits)
    // , => =>implements(interface)
    lateinit var rbBayar : RadioButton
    val pilBerangkat = arrayOf<String>("06.00 pm", "06.00 am", "10.00 pm", "08.00 am" , "11.00 pm", "09.00 am", "03.00 am", "10.00 am", "04.00 am","11.00 am")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dua)
        val makanan = findViewById<CheckBox>(R.id.chkMakanan)
        val executive = findViewById<CheckBox>(R.id.chkExecutive)
        val bagasi = findViewById<CheckBox>(R.id.chkBagasi)
        val bayar = findViewById<RadioGroup>(R.id.rgBayar)
        val tujuan = findViewById<EditText>(R.id.editTujuan)
        val harga = findViewById<EditText>(R.id.editHarga)
        val jumlah = findViewById<EditText>(R.id.editJumlah)
        val proses = findViewById<Button>(R.id.btnProses)
        val trans = findViewById<TextView>(R.id.txtTrans)
        val spinBerangkat = findViewById<Spinner>(R.id.spinBerangkat)
        spinBerangkat.onItemSelectedListener=this
        val aa:ArrayAdapter<*> = ArrayAdapter<Any?>(this@DuaActivity,R.layout.spinner_style,pilBerangkat)
        aa.setDropDownViewResource(R.layout.spinner_style)
        spinBerangkat.adapter = aa
        proses.setOnClickListener {
            val tujuanP = tujuan.text.toString()
            val hargaP = harga.text.toString().toDouble()
            val jumlahP = jumlah.text.toString().toInt()
            val totTiket = hargaP * jumlahP
            val berangkat = spinBerangkat.selectedItem.toString()
            var totalBayar:Int=0
            val tambahan = StringBuffer()//object kotlin

            if (executive.isChecked){
                tambahan.append("\n\t Excutive Lounge Rp.125000")
                totalBayar += 125000
            }
            if (bagasi.isChecked){
                tambahan.append("\n\t Bagasi Rp.50000")
                totalBayar += 50000
            }
            if (makanan.isChecked){
                tambahan.append("\n\t Makanan / Minuman Rp.100000")
                totalBayar += 100000
            }
            tambahan.append("\n Total Biaya Tambahan : "+totalBayar)
            val selectedRadio:Int = bayar.checkedRadioButtonId
            var total = totTiket + totalBayar
            rbBayar= findViewById(selectedRadio)
            trans.text = "Detail Pembayaran : " +
                    "\n Tujuan : $tujuanP " +
                    "\n Harga Tiket : $hargaP " +
                    "\n Jam Berangkat : $berangkat" +
                    "\n Biaya Tambahan : " +tambahan.toString()+
                    "\n Jumlah Tiket : $jumlahP" +
                    "\n Total Biaya tiket : $totTiket"+
                    "\n Metode Pembayaran : " +rbBayar.text.toString()+
                    "\n Total Bayar : $total"

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val pilJamBerangkat :String = parent?.getItemAtPosition(position).toString()
//        Toast.makeText(this,"Jam Berangkat : $pilJamBerangkat",Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}