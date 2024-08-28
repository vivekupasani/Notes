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
import com.vivekupasani.todolist.databinding.FragmentEditBinding
import java.sql.Timestamp
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class Edit : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private val viewModel: notesViewModel by viewModels()
    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)

        arguments.let {
            val title = it?.getString("title")
            val description = it?.getString("description")
            val date = it?.getString("date")
            val id = it?.getString("id")

            binding.Edittitle.setText(title)
            binding.EditDescription.setText(description)

            val currentDate = LocalDate.now()
            val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

            binding.btnEdit.setOnClickListener { it ->
                val notes = notesEntity(
                    id!!.toInt(),
                    binding.Edittitle.text.toString(),
                    binding.EditDescription.text.toString(),
                    formattedDate,
                    Timestamp(System.currentTimeMillis()),
                    0
                )
                viewModel.updateNote(notes)

                navController = Navigation.findNavController(it)
                navController.navigate(R.id.action_edit_to_home2)

                Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


}