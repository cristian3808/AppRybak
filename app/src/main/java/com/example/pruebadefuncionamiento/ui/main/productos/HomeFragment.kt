package com.example.pruebadefuncionamiento.ui.main.productos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebadefuncionamiento.R

class HomeFragment : Fragment() {
    private val listaProductos = listOf(
        Product("Camiseta de algodón", 10.0, R.drawable.camiseta),
        Product("Pantalon", 20.0, R.drawable.pantalon),
        Product("Zapatos", 10.0, R.drawable.zapatos),
        Product("Bolso de mano", 20.0, R.drawable.bolso)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_productos)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2) //Depende del diseño
        recyclerView.adapter = ProductAdapter(listaProductos)
        return view
    }
}