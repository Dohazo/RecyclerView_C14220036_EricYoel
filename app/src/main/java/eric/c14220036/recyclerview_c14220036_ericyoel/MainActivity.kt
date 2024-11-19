package eric.c14220036.recyclerview_c14220036_ericyoel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var _nama : Array<String>
    private lateinit var _karakter : Array<String>
    private lateinit var _deskripsi : Array<String>
    private lateinit var _gambar : Array<String>

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
        _nama = resources.getStringArray(R.array.namaWayang)
        _deskripsi = resources.getStringArray(R.array.deskripsiWayang)
        _karakter = resources.getStringArray(R.array.karakterUtamaWayang)
        _gambar = resources.getStringArray(R.array.gambarWayang)
    }
    fun tambahdata(){
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
        })

//        _rvWayang.layoutManager = LinearLayoutManager(this)
//        _rvWayang.adapter = adapterRevView(arWayang)
    }
}