package com.vivekupasani.todolist.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.vivekupasani.todolist.R
import com.vivekupasani.todolist.ViewModels.notesViewModel
import com.vivekupasani.todolist.databinding.FragmentViewBinding

class View : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentViewBinding
    val viewModel: notesViewModel by viewModels()
    lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewBinding.inflate(inflater, container, false)

        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.delete_dialog)

        binding.btnBackStack.setOnClickListener {
            navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_view2_to_home22)
        }

        arguments?.let {
            val title = it.getString("title")
            val description = it.getString("description")
            val date = it.getString("date")
            val id = it.getString("id")

            binding.viewTitle.text = title
            binding.viewDescription.text = description
            binding.viewDate.text = date

            binding.btnDelete.setOnClickListener {
                showCustomDialog(id)
            }

            binding.btnUpdate.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("title", title)
                    putString("description", description)
                    putString("date", date)
                    putString("id", id)
                }
                navController = Navigation.findNavController(it)
                navController.navigate(R.id.action_view2_to_edit, bundle)
            }
        }

        return binding.root
    }

    private fun showCustomDialog(id: String?) {
        if (id == null) return  // Ensure id is not null

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)

        val dialogView: View =
            LayoutInflater.from(requireContext()).inflate(R.layout.delete_dialog, null)
        dialog.setContentView(dialogView)

        val btnSure: Button = dialogView.findViewById(R.id.btnSure)
        val btnCancel: Button = dialogView.findViewById(R.id.btnCancle)

        btnSure.setOnClickListener {
            // Handle the "Yes" button click
            viewModel.deleteNote(id.toInt())
            dialog.dismiss()

            navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_view2_to_home22)

            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
        }

        btnCancel.setOnClickListener {
            // Handle the "Cancel" button click
            dialog.dismiss()
        }

        dialog.show()
    }
}
