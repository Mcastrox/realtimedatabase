package com.example.tutoapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tutoapp.databinding.FragmentSearchBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container, false)

        search(binding)

        return binding.root
    }

    fun search(binding: FragmentSearchBinding){
        toolbar = binding.toolbar
        toolbar?.setTitle(R.string.ToolBarTitle)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        var actionBar = activity?.actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        var me = this // variable para guardar el contexto actual

        var listaTutores = mutableListOf<Model>()
        val ref = FirebaseDatabase.getInstance().getReference("Users") // referencia a la bd

        //Aqui se trae todos los valores
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
                    var rol: String = ""
                    var ruta : String = ""
                    var id : String = e.child("ID").value as String

                    if (e.child("lastName").value != null) {
                        lastName = e.child("lastName").value as String
                    }
                    if (e.child("direccion").value != null) {
                        direccion = e.child("direccion").value as String
                    }
                    if (e.child("Rol").value != null) {
                        rol = e.child("Rol").value as String
                    }
                    if(e.child("urlImage").value != null){
                        ruta=e.child("urlImage").value as String
                    }


                    if (rol == "Tutor") {
                        listaTutores.add(Model(id,lastName, direccion,R.drawable.ic_art,ruta))
                    }

                }


                var adapter = TutorAdapter(activity!!, listaTutores)
                list.adapter = adapter

                list.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(activity,PseleccionadoActivity::class.java)
                    intent.putExtra("tutor", listaTutores[position])
                    startActivity(intent)
                }
            }

        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,inflater)
        val itemBusqueda =menu?.findItem(R.id.busqueda)
        var vistaBusqueda=itemBusqueda?.actionView as SearchView
        val itemCompartir=menu?.findItem(R.id.share)
        val shareActionProvider= MenuItemCompat.getActionProvider(itemCompartir) as androidx.appcompat.widget.ShareActionProvider
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.bFav -> {
                Toast.makeText(activity, "Elemento agregado a favoritos", Toast.LENGTH_SHORT).show()
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
