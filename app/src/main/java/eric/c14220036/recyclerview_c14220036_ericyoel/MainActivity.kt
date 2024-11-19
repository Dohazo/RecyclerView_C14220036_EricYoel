package eric.c14220036.recyclerview_c14220036_ericyoel

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var _nama : MutableList<String>
    private lateinit var _karakter :  MutableList<String>
    private lateinit var _deskripsi :  MutableList<String>
    private lateinit var _gambar :  MutableList<String>

    private lateinit var _rvWayang : RecyclerView
    private  var arWayang = arrayListOf<wayang>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        _rvWayang = findViewById<RecyclerView>(R.id.rvWayang)
        siapkanData()
        tambahdata()
        tampilkanData()
    }
    fun siapkanData(){
        _nama = resources.getStringArray(R.array.namaWayang).toMutableList()
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang).toMutableList()
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang).toMutableList()
        _gambar = resources.getStringArray(R.array.gambarWayang).toMutableList()
    }
    fun tambahdata(){
        arWayang.clear()
        for(position in _nama.indices){
            val data = wayang(
                _gambar[position],
                _nama[position],
                _karakter[position],
                _deskripsi[position]
            )
            arWayang.add(data)
        }
    }
    fun tampilkanData(){
        _rvWayang.layoutManager = LinearLayoutManager(this)


//        _rvWayang.layoutManager = StaggeredGridLayoutManager(
//            2,
//            LinearLayoutManager.VERTICAL
//            )
//        _rvWayang.layoutManager = GridLayoutManager(this,2)
        var adapterWayang = adapterRevView(arWayang)
        _rvWayang.adapter = adapterWayang
        adapterWayang.setOnItemClickCallback(object : adapterRevView.OnItemClickCallback{
            override fun onItemClicked(data: wayang) {
//                Toast.makeText(this@MainActivity, data.nama,Toast.LENGTH_LONG)
//                    .show()
                val intent = Intent(this@MainActivity, detWayang::class.java)
                intent.putExtra("kirimData",data)
                startActivity(intent)
            }

            override fun delData(pos: Int) {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Hapus Data")
                    .setMessage("Apakah Benar Data " + _nama[pos] + " akan dihapus ?")
                    .setPositiveButton(
                        "HAPUS",
                        DialogInterface.OnClickListener{dialog, which ->
                            _gambar.removeAt(pos)
                            _nama.removeAt(pos)
                            _deskripsi.removeAt(pos)
                            _karakter.removeAt(pos)
                            tambahdata()
                            tampilkanData()
                        }
                    )
                    .setNegativeButton(
                        "BATAL",
                        DialogInterface.OnClickListener{dialog,which ->
                            Toast.makeText(
                                this@MainActivity,
                                "Data Batal Dihapus",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    ).show()
            }
        })

//        _rvWayang.layoutManager = LinearLayoutManager(this)
//        _rvWayang.adapter = adapterRevView(arWayang)
    }
}