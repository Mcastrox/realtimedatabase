package com.example.tutoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_mostrar.*

class MostrarActivity : AppCompatActivity() {
    private lateinit var uid: String
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)
        /*var tutor = Model("Facebook", "Red social", R.drawable.ic_chef)
        var tutor1 = Model("WhatsApp", "Red social", R.drawable.ic_laptop)
        var tutor2 = Model("Instagram", "Red social", R.drawable.ic_art)*/

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.ToolBarTitle)
       setSupportActionBar(toolbar)
        var actionBar =supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        var me = this // variable para guardar el contexto actual
        var listaTutores = mutableListOf<Model>()
        val ref = FirebaseDatabase.getInstance().getReference("Users") // referencia a la bd
        //qui traigo todos los valores
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (listaTutores.size > 0) {
                    listaTutores.clear()
                    list.adapter = null
                }
                for (e in p0.children) {
                    var lastName: String = ""
                    var direccion: String = ""
                    var rol : String=""
                    if (e.child("lastName").value != null) {
                        lastName = e.child("lastName").value as String
                    }
                    if (e.child("direccion").value != null) {
                        direccion = e.child("direccion").value as String
                    }
                    if(e.child("Rol").value != null){
                        rol=e.child("Rol").value as String
                    }
                    if(rol == "Tutor" )
                    {
                        listaTutores.add(Model(lastName, direccion, R.drawable.ic_laptop))
                    }

                }
                var adapter = TutorAdapter(me, listaTutores)
                list.adapter = adapter

                list.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(me, PseleccionadoActivity::class.java)
                    intent.putExtra("tutor", listaTutores[position])
                    startActivity(intent)
                }
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val itemBusqueda =menu?.findItem(R.id.busqueda)
        var vistaBusqueda=itemBusqueda?.actionView as SearchView
        val itemCompartir=menu?.findItem(R.id.share)
        val shareActionProvider=MenuItemCompat.getActionProvider(itemCompartir) as androidx.appcompat.widget.ShareActionProvider
        compartirIntent(shareActionProvider)


        vistaBusqueda.queryHint="Categoria.."
        vistaBusqueda.setOnQueryTextFocusChangeListener { v, hasFocus ->
            Log.d("ListenerFocus",hasFocus.toString())
        }

        vistaBusqueda.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("OnQueryTextSubmit",query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("OnQueryTextChange",newText)
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.bFav -> {
                Toast.makeText(this, "Elemento agregado a favoritos", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> (return super.onOptionsItemSelected(item))

        }
    }
    private fun compartirIntent(shareActionProvider:androidx.appcompat.widget.ShareActionProvider){
        if(shareActionProvider!=null){
            val intent=Intent(Intent.ACTION_SEND)
            //aqui se especifica el tipo de dato que se va a compartir
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Este es un mensaje compartido")
            shareActionProvider.setShareIntent(intent)
        }
    }

}
