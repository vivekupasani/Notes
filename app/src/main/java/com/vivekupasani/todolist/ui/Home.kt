package com.vivekupasani.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivekupasani.todolist.R
import com.vivekupasani.todolist.ViewModels.notesViewModel
import com.vivekupasani.todolist.databinding.FragmentHomeBinding
import com.vivekupasani.todolist.ui.Adapters.notesAdapter

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private lateinit var adapter: notesAdapter
    private val viewModel: notesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnClickAdd.setOnClickListener {
            navController.navigate(R.id.action_home2_to_add2)
        }

        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
            val sortedNotes = notesList.sortedByDescending { it.timestamp }
            adapter = notesAdapter(requireContext(), sortedNotes)
            binding.taskRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.taskRecyclerView.adapter = adapter
        })

        binding.btnSetting.setOnClickListener {

            navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_home2_to_profile)
        }


        return binding.root
    }
}
