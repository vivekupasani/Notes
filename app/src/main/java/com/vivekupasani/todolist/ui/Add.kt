package com.vivekupasani.todolist.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.vivekupasani.todolist.Entity.notesEntity
import com.vivekupasani.todolist.R
import com.vivekupasani.todolist.ViewModels.notesViewModel
import com.vivekupasani.todolist.databinding.FragmentAddBinding
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter

class Add : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val viewModel: notesViewModel by viewModels()
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.btnAdd.setOnClickListener {
            if (binding.title.text.isNotEmpty() && binding.description.text.isNotEmpty()) {
                addData(it)
            } else {
                Toast.makeText(requireContext(), "Fill the Blanks", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addData(it: View?) {
        val name = binding.title.text.toString()
        val description = binding.description.text.toString()
        val priority = 1
        val currentDate = now()
        val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        val notes = notesEntity(
            id = null,
            name = name,
            description = description,
            date = formattedDate,
            timestamp = Timestamp(System.currentTimeMillis()),
            priority = priority
        )
        viewModel.insertNote(notes)

        Toast.makeText(requireContext(), "Added", Toast.LENGTH_SHORT).show()

        navController = Navigation.findNavController(it!!)
        navController.navigate(R.id.action_add2_to_home2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
