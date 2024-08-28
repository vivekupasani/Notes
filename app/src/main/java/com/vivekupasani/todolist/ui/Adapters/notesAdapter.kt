package com.vivekupasani.todolist.ui.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.vivekupasani.todolist.Entity.notesEntity
import com.vivekupasani.todolist.R
import com.vivekupasani.todolist.databinding.EachTaskDesignBinding


class notesAdapter(val context: Context, val notesList: List<notesEntity>) :
    RecyclerView.Adapter<notesAdapter.viewHolder>() {
    class viewHolder(val binding: EachTaskDesignBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = EachTaskDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.binding.apply {
            showTitle.text = currentNote.name
            showDescription.text = currentNote.description
            showDate.text = currentNote.date
        }

        holder.binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                putString("title", currentNote.name)
                putString("description", currentNote.description)
                putString("date", currentNote.date)
                putString("id", currentNote.id.toString())
            }

            Navigation.findNavController(it).navigate(R.id.action_home2_to_view2, bundle)


        }
    }

}